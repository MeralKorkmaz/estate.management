//package estate.management.com.payload.mapper;
//
//import estate.management.com.domain.advert.Advert;
//import estate.management.com.payload.request.AdvertRequest;
//import estate.management.com.payload.response.AdvertResponse;
//import lombok.Data;
//import org.springframework.stereotype.Component;
//
//@Data
//@Component
//public class AdvertMapper {
//
//
//    public AdvertResponse mapAdvertToAdvertResponse(Advert advert){
//        return AdvertResponse.builder()
//                .advertTypeId(advert.getAdvertTypeId())
//                .id(advert.getId())
//                .price(advert.getPrice())
//                .cityId(advert.getCityId())
//                .slug(advert.getSlug())
//                .countryId(advert.getCountryId())
//                .districtId(advert.getDistrictId())
//                .title(advert.getTitle())
//                .categoryId(advert.getCategoryId())
//                .location(advert.getLocation())
//                .description(advert.getDescription())
//                .status(advert.getStatus())
//                .viewCount(advert.getViewCount())
//                .userId(advert.getUserId())
//                .build();
//
//    }
//    public Advert mapAdvertRequestToAdvert(AdvertRequest advertRequest){
//        return Advert.builder()
//                .advertTypeId(advertRequest.getAdvertTypeId())
//                .built_in(advertRequest.getBuilt_in())
//                .price(advertRequest.getPrice())
//                .cityId(advertRequest.getCityId())
//                .slug(advertRequest.getSlug())
//                .countryId(advertRequest.getCountryId())
//                .districtId(advertRequest.getDistrictId())
//                .title(advertRequest.getTitle())
//                .categoryId(advertRequest.getCategoryId())
//                .location(advertRequest.getLocation())
//                .description(advertRequest.getDescription())
//                .status(advertRequest.getStatus())
//                .viewCount(advertRequest.getViewCount())
//                .build();
//    }
//}
