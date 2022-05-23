package com.nocountry.travel.model;

import com.nocountry.travel.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

@Entity
@Table(name = "acommodations")
@SQLDelete(sql = "UPDATE acommodations SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Acommodation extends BaseEntity {

    @NotEmpty(message = "{error.blank.field}")
    private String name;

    @Min(value = 0, message = "{error.rating.min}")
    @Max(value = 5, message = "{error.rating.max}")
    private int rating;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Min(value = 0, message = "{error.price.min}")
    private Float price;

    @NotEmpty(message = "{error.blank.field}")
    private String location;

    @NotEmpty(message = "{error.blank.field}")
    private String contact;

    private ArrayList<String> urlsImages = new ArrayList<>();

}

