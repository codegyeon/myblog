package com.sparta.hanghae.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Blog extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id ;

    @Column
    private String title;

    @Column
    private String name;

    @Column
    private String contents;

    public Blog(String title, String name, String contents) {
        this.title = title;
        this.name = name;
        this.contents = contents;
    }

    public Blog(BlogRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.name = requestDto.getName();
        this.contents = requestDto.getContents();
    }

    public void update(BlogRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.name = requestDto.getName();
        this.contents = requestDto.getContents();
    }

}
