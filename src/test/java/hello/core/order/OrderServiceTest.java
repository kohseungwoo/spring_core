package hello.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;

public class OrderServiceTest {
    
    // MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository());
    // OrderService orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());

    MemberService memberService;
    OrderService orderService;


    @BeforeEach
    public void BeforeEach(){
        AppConfig config = new AppConfig();
        this.memberService = config.memberService();
        this.orderService = config.orderService();
    }

    @Test
    void creatOrder(){
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.creatOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    
    }
}
