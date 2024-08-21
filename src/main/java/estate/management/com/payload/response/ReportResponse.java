package estate.management.com.payload.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse {

    private long categories;
    private long adverts;
    private long advertTypes;
    private long tourRequests;
    private long customers;

}
