package main.java.common.solutions.serialization;

import main.java.cargo.domain.Cargo;
import main.java.cargo.domain.CargoType;
import main.java.cargo.domain.LimitedShelfLife;
import main.java.cargo.domain.UnlimitedShelfLife;
import main.java.carrier.domain.Carrier;
import main.java.transportation.domain.Transportation;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

public class ExporterTxtFile {
    private static StringBuilder exportStringBuilder;
    private static FileWriter writer;
    private static int index;

    private ExporterTxtFile() {
    }

    public static boolean exportIntoTxt(List<Cargo> list, List<Carrier> list2, List<Transportation> list3) {
        try {
            Path path = createFile();
            writeCargos(list, path);
            writeCarriers(list2);
            writeTransportation(list3);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void writeCargos(List<Cargo> list, Path path) throws IOException {
        writer = new FileWriter(path.toFile());
        exportStringBuilder = new StringBuilder();
        StringBuilder diffString = new StringBuilder();
        index = 1;
        exportStringBuilder.append("\t\t\t\tCARGOS\n");

        for (Cargo cargo : list) {
            if (isLimitedTimeShelfCargo(cargo.getCargoType())) {
                diffString.append(" dateProduced:")
                        .append(((LimitedShelfLife) cargo).getProduced())
                        .append(" dateExpires:").append(((LimitedShelfLife) cargo).getExpires())
                        .append(" lim");
            } else {
                diffString.append(" isComposite:")
                        .append(((UnlimitedShelfLife) cargo).isComposite())
                        .append(" fragility:").append(((UnlimitedShelfLife) cargo).isFragility())
                        .append(" unlim");
            }
            exportStringBuilder.append(index).append(". ")
                    .append("name:").append(cargo.getName())
                    .append(" weight:").append(cargo.getWeight())
                    .append(" cargoType:").append(cargo.getCargoType())
                    .append(" transportations:").append(cargo.getTransportations())
                    .append(diffString)
                    .append(System.lineSeparator());
            diffString = new StringBuilder();
            index++;
        }
        flushAndWriteLineSeparator();
    }

    private static void writeCarriers(List<Carrier> list2) throws IOException {
        exportStringBuilder = new StringBuilder();
        index = 1;
        exportStringBuilder.append("\t\t\t\tCARRIERS\n");

        for (Carrier carrier : list2) {
            exportStringBuilder.append(index).append(". ")
                    .append("name:").append(carrier.getName())
                    .append(" address:").append(carrier.getAddress())
                    .append(" carrierType:").append(carrier.getCarrierType())
                    .append(" transportations:").append(carrier.getTransportations())
                    .append(System.lineSeparator());
            index++;
        }
        flushAndWriteLineSeparator();
    }

    private static void writeTransportation(List<Transportation> list3) throws IOException {
        exportStringBuilder = new StringBuilder();
        index = 1;
        exportStringBuilder.append("\t\t\t\tTRANSPORTATIONS\n");

        for (Transportation transportation : list3) {
            exportStringBuilder.append(index).append(". ")
                    .append("description:").append(transportation.getDescription())
                    .append(" billTo:").append(transportation.getBillTo())
                    .append(" date:").append(transportation.getDate()).append(" ")
                    .append(transportation.getCargo().getId()).append("_")
                    .append(transportation.getCarrier().getId())
                    .append(" transportations:null")
                    .append(System.lineSeparator());
            index++;
        }
        flushAndWriteLineSeparator();
        writer.close();
    }

    private static Path createFile() throws IOException {
        LocalDateTime localDateTime = LocalDateTime.now();
        String dateNow = String.valueOf(localDateTime.toLocalDate());
        StringBuilder filename = new StringBuilder(dateNow).append(".txt");
        Path path = Paths.get("D:\\JAVA\\EPAM_SPB\\Training\\12_class\\src\\main\\java\\resources\\out\\" + String.valueOf(filename));
        if (!Files.exists(path)) {
            Files.createFile(path);
        } else {
            Files.delete(path);
            Files.createFile(path);
        }
        return path;
    }

    private static boolean isLimitedTimeShelfCargo(CargoType cargoType) {
        return cargoType.equals(CargoType.FOOD);
    }

    private static void flushAndWriteLineSeparator() throws IOException {
        writer.write(exportStringBuilder.append("\n").toString());
        writer.flush();
    }
}
