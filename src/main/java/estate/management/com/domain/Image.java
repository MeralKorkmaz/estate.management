
package estate.management.com.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import estate.management.com.domain.advert.Advert;
import lombok.*;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Lob
    private byte[] data;
    @Column(name="name",nullable = false)
    @NotNull
    private String name;
    @Column(nullable = true)
    private String type;
    @Column(name="featured",nullable = false)
    @NotNull
    private boolean featured=false;


    @ManyToOne
    @JoinColumn(name = "images")
    private Advert advert;



}
