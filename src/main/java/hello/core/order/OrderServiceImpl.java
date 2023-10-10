package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

//단일책임원칙 SRP를 수행중
public class OrderServiceImpl implements OrderService{
    
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    
    /**
     * 추상화/구체화에 의존상태
     * 할인 정책을 변경하려면 클라이언트인 OrderServiceImpl 코드를 수정해야함. 문제발생 OCP위반
     */
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    /**
     * 인터페이스에만 의존하도록 코드변경
     * 그런데 구현체가 없는데 어떻게 코드를 실행할까?
     * 실제 실행해보면 NPE(null pointer exception) 발생
     * 
     * 해결방안
     * 이 문제를 해결하려면 누군가가 클라이언트인 OrderSerivceImpl에 DiscountPolicy 의 구현 객체를 대신 생성하고 주입해주어야 한다.
    */
    /* DIP 수행, Interface에만 의존 */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order creatOrder(Long memberId, String itemName, int itemPrice){
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);

    }
}
