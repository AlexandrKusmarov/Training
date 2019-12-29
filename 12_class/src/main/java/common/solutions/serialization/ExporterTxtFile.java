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

    public static boolean exportIntoTxt(List<Cargo> list, List<Carrier> list2, List<Transportation> list3) {
        try {
            Path path = createFile();
            writeCargos(list, path);
            writeCarriers(list2, path);
            writeTransportation(list3, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void writeCargos(List<Cargo> list, Path path) throws IOException {
        FileWriter writer = new FileWriter(path.toFile());
        StringBuilder cargoString = new StringBuilder();
        StringBuilder diffString = new StringBuilder();
        int index = 1;
        cargoString.append("\t\t\t\tCARGOS\n");
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
            cargoString.append(index).append(". ")
                    .append("name:").append(cargo.getName())
                    .append(" weight:").append(cargo.getWeight())
                    .append(" cargoType:").append(cargo.getCargoType())
                    .append(" transportations:").append(cargo.getTransportations())
                    .append(diffString)
                    .append(System.lineSeparator());
            diffString = new StringBuilder();
            index++;
        }
        writer.write(cargoString.append("\n").toString());
        writer.flush();
        writer.close();
    }

    private static boolean isLimitedTimeShelfCargo(CargoType cargoType) {
        return cargoType.equals(CargoType.FOOD);
    }

    private static void writeCarriers(List<Carrier> list2, Path path) {
    }

    private static void writeTransportation(List<Transportation> list3, Path path) {

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
}
