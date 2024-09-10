

package estate.management.com.payload.mapper;

import estate.management.com.domain.Image;
import estate.management.com.domain.administrative.City;
import estate.management.com.domain.advert.Advert;
import estate.management.com.domain.category.CategoryPropertyKey;
import estate.management.com.domain.category.CategoryPropertyValue;
import estate.management.com.payload.request.advert.AdvertRequest;

import estate.management.com.payload.response.ImageResponse;
import estate.management.com.payload.response.concrete.advert.CityResponseForAdvert;
import estate.management.com.payload.response.concrete.advert.AdvertResponse;
import estate.management.com.payload.response.concrete.advert.PropertyResponse;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class AdvertMapper {

    public  AdvertResponse toAdvertResponse(Advert advert) {
        return AdvertResponse.builder()
                .id(advert.getId())
                .title(advert.getTitle())
                .description(advert.getDescription())
                .price(advert.getPrice())
                .advertTypeId(advert.getAdvertTypeId())
                .countryId(advert.getCountryId())
                .districtId(advert.getDistrictId())
                .location(advert.getLocation())
                .isActive(advert.isActive())
                .viewCount(advert.getViewCount())
                .slug(advert.getSlug())
                .viewCount(advert.getViewCount())
                .properties(
                        advert.getCategoryPropertyValues().stream().map(
                                prop -> PropertyResponse.builder()
                                        .keyId(prop.getCategoryPropertyKey().getId())
                                        .value(prop.getValue())
                                        .build()
                        ).collect(Collectors.toList())
                )
                .images(
                        advert.getImages().stream().map(
                                img -> ImageResponse.builder()
                                        .name(img.getName())
                                        .data(img.getData())
                                        .type(img.getType())
                                        .featured(img.isFeatured())
                                        .build()
                        ).collect(Collectors.toList())
                )
                .build();
    }
    public Advert toAdvert(AdvertRequest advertRequest) {
        return Advert.builder()
                .title(advertRequest.getTitle())
                .description(advertRequest.getDescription())
                .price(advertRequest.getPrice())
                .advertTypeId(advertRequest.getAdvertTypeId())
                .countryId(advertRequest.getCountryId())
                .districtId(advertRequest.getDistrictId())
                .location(advertRequest.getLocation())
                .slug(advertRequest.getSlug())
                .viewCount(advertRequest.getViewCount())
                .categoryPropertyValues(
                        advertRequest.getProperties().stream().map(
                                prop -> CategoryPropertyValue.builder()
                                        .categoryPropertyKey(CategoryPropertyKey.builder().id(prop.getKeyId()).build())
                                        .value(prop.getValue())
                                        .build()
                        ).collect(Collectors.toList())
                )
                .images(
                        advertRequest.getImages().stream().map(
                                img -> Image.builder()
                                        .name(img.getName())
                                        .data(img.getData())
                                        .type(img.getType())
                                        .featured(img.isFeatured())
                                        .build()
                        ).collect(Collectors.toList())
                )
                .build();
    }




}
