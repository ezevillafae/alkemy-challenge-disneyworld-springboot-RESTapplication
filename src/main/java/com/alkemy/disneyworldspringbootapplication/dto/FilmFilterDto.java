package com.alkemy.disneyworldspringbootapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmFilterDto {
    private String id;
    private String title;
    private Set<Long> idGenres;
    private String order;


    public boolean isASC(){
        return this.order.equalsIgnoreCase("ASC");
    }

    public boolean isDESC(){
        return this.order.equalsIgnoreCase("DESC");
    }
}
