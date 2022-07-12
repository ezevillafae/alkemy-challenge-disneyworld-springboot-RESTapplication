package com.alkemy.disneyworldspringbootapplication.repository.specification;

import com.alkemy.disneyworldspringbootapplication.dto.FilmFilterDto;
import com.alkemy.disneyworldspringbootapplication.entity.FilmEntity;
import com.alkemy.disneyworldspringbootapplication.entity.GenreEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


@Component
public class FilmSpecification {

    public Specification<FilmEntity> getByFilters(FilmFilterDto filterDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if(StringUtils.hasLength(filterDto.getId())){
                predicateList.add(
                        criteriaBuilder.equal(
                                root.get("id"),
                                filterDto.getId()
                        )
                );
            }


            if(StringUtils.hasLength(filterDto.getTitle())){
                predicateList.add(
                        criteriaBuilder.like(
                                root.get("title"),
                                "%" + filterDto.getTitle() + "%"
                        )
                );
            }

            if(!CollectionUtils.isEmpty(filterDto.getIdGenres())){
                Join<GenreEntity, FilmEntity> join = root.join("genres", JoinType.INNER);
                Expression<String> genresId = join.get("id");
                predicateList.add(genresId.in(filterDto.getIdGenres()));
            }

            query.distinct(true);

            if(StringUtils.hasLength(filterDto.getOrder())){
                String orderField = "creationDate";
                query.orderBy(
                        filterDto.isASC()?
                                criteriaBuilder.asc(root.get(orderField)):
                                criteriaBuilder.desc(root.get(orderField))
                );
            }

            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
