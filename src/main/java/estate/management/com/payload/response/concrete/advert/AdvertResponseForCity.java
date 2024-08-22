package estate.management.com.payload.response.concrete.advert;

import estate.management.com.domain.administrative.City;
import estate.management.com.payload.response.concrete.CityResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdvertResponseForCity {

 private  CityResponse[] cityResponses;


}
