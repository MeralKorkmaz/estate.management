package estate.management.com.domain.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
        @NotNull
        @Column(name = "advert_id", nullable = false)
        private int advertId;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_property_key_id", nullable = false)
    @JsonIgnore
    private CategoryPropertyKey categoryPropertyKey;

        public Long getId() {
                return id;
        }
        public void setId(Long id) {
                this.id = id;
        }
        public String getValue() {
                return value;
        }
        public void setValue(String value) {
                this.value = value;
        }
        public int getAdvertId() {
                return advertId;
        }
        public void setAdvertId(int advertId) {
                this.advertId = advertId;
        }
        public CategoryPropertyKey getCategoryPropertyKey() {
                return categoryPropertyKey;
        }
        public void setCategoryPropertyKey(CategoryPropertyKey categoryPropertyKey) {
                this.categoryPropertyKey = categoryPropertyKey;
        }


}