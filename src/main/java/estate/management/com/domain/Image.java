package estate.management.com.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Setter;

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


    @Column(name="advert_id",nullable = false)
    private int advertId;
}
