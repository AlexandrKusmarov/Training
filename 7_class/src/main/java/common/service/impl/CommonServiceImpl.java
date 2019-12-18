package main.java.common.service.impl;

import main.java.common.repo.CommonRepo;
import main.java.common.repo.impl.CommonRepoImpl;
import main.java.common.service.CommonService;

public class CommonServiceImpl implements CommonService {
    private CommonRepo commonRepo = new CommonRepoImpl();

    @Override
    public boolean deleteById(Long id) {
        return commonRepo.deleteById(id);
    }
}
