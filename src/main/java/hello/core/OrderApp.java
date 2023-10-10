package hello.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;

public class OrderApp {
    
    public static void main(String[] args) {
        // MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository());
        // OrderService orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());

        // 스프링 사용 전
        // AppConfig config = new AppConfig();
        // MemberService memberService = config.memberService();
        // OrderService orderService = config.orderService();

        // 스프링 사용 후
        // 스프링 컨테이너 ApplicationContext 를 스프링 컨테이너라 한다.
        // 기존에는 개발자가 AppConfig를 사용해서 직접 객체를 생성하고 DI를 했지만, 이제부터는 스프링 컨테이너를 통해서 사용한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService",  OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.creatOrder(memberId, "memberA", 20000);

        System.out.println("order = " + order);
        System.out.println("order.calulatePrice = " + order.calculatePrice());
        
    }
}
