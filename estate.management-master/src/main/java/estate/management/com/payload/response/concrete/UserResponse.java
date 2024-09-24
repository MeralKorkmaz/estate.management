package estate.management.com.payload.response.concrete;

import com.fasterxml.jackson.annotation.JsonInclude;

import estate.management.com.payload.response.abstracts.BaseUserResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
public class UserResponse extends BaseUserResponse {
}