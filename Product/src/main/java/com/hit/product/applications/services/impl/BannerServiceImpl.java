package com.hit.product.applications.services.impl;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.configs.exceptions.NotFoundException;
import com.hit.product.applications.repositories.BannerRepository;
import com.hit.product.applications.services.BannerService;
import com.hit.product.applications.utils.UploadFile;
import com.hit.product.domains.dtos.BannerDto;
import com.hit.product.domains.entities.Banner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    BannerRepository bannerRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UploadFile uploadFile;

    @Override
    public List<Banner> getBanners() {
        return bannerRepository.findAll();
    }

    @Override
    public Banner getBannerById(Long id) {
        Optional<Banner> banner = bannerRepository.findById(id);
        checkBannerException(banner);
        return banner.get();
    }

    @Override
    public Banner uploadImgBanner(Long id, MultipartFile multipartFile) {
        Optional<Banner> banner = bannerRepository.findById(id);
        checkBannerException(banner);

        if(banner.get().getImgUrl() != null) {
            uploadFile.removeFileFromUrl(banner.get().getImgUrl());
        }
        banner.get().setImgUrl(uploadFile.getUrlFromFile(multipartFile));

        return bannerRepository.save(banner.get());
    }

    @Override
    public Banner createBanner(BannerDto bannerDto) {
        return createOrUpdate(new Banner(), bannerDto);
    }

    @Override
    public Banner updateBanner(Long id, BannerDto bannerDto) {
        Optional<Banner> banner = bannerRepository.findById(id);
        checkBannerException(banner);

        return createOrUpdate(banner.get(), bannerDto);
    }

    private Banner createOrUpdate(Banner banner, BannerDto bannerDto) {
        modelMapper.map(bannerDto, banner);
        return bannerRepository.save(banner);
    }

    @Override
    public TrueFalseResponse deleteBanner(Long id) {
        Optional<Banner> banner = bannerRepository.findById(id);
        checkBannerException(banner);
        bannerRepository.deleteById(id);
        return new TrueFalseResponse(true);
    }

    private void checkBannerException(Optional<Banner> banner) {
        if(banner.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }
}
