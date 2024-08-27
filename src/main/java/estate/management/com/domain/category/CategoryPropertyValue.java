package estate.management.com.domain.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import estate.management.com.domain.advert.Advert;
import estate.management.com.payload.request.CategoryPropertyValueRequest;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
@Entity
@Table(name = "category_property_value")
public class CategoryPropertyValue {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        @Column(name = "value", nullable = false, length = 100)
        @NotNull(message = "value cannot be null")
        @Size(max = 100, message = "value must be less than {max} characters")
        private String value;
       @ManyToOne
       @JoinColumn(name = "category_property_values")
       private Advert advert;

       @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
       @JoinColumn(name = "category_property_key_id", nullable = false)
       @JsonIgnore
       private CategoryPropertyKey categoryPropertyKey;
}