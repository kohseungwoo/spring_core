package hello.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;

public class MemberApp {
    public static void main(String[] args) {
        // MemberService memberService = new MemberServiceImpl();

        // AppConfig config = new AppConfig();
        // MemberService memberService = config.memberService();

        // spring 사용
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); // 기본적으로 Bean에 등록되어있는 메서드 이름을 찾는다.

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = "+ member.getName());
        System.out.println("find member = "+ findMember.getName());
    }
}
