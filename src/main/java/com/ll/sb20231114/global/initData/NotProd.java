package com.ll.sb20231114.global.initData;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ll.sb20231114.domain.article.article.service.ArticleService;
import com.ll.sb20231114.domain.member.member.entity.Member;
import com.ll.sb20231114.domain.member.member.service.MemberService;

@Configuration
public class NotProd {
    @Bean
    public ApplicationRunner initNotProd(
            MemberService memberService,
            ArticleService articleService
    ) {
        return args -> {
            Member memberAdmin = memberService.join("admin", "1234").getData();
            Member memberUser1 = memberService.join("user1", "1234").getData();
            Member memberUser2 = memberService.join("user2", "1234").getData();

            articleService.write(memberAdmin, "제목1", "내용1");
            articleService.write(memberUser1, "제목2", "내용2");
            articleService.write(memberUser1, "제목3", "내용3");
        };
    }
}
