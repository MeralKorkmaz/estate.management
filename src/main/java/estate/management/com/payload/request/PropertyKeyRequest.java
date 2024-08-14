package estate.management.com.payload.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
public class PropertyKeyRequest {

    private String name;
    private Boolean built_in = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

    

