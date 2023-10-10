package hello.core.member;

public class MemberServiceImpl implements MemberService{
    
    //다형성에 의해서 MemoryMemberRepository에 있는 save가 호출된다.
    //추상화/구체화 2곳에 의존하는 상태이다. (MemberRepository, MemoryMemberRepository) -> DIP 위반 상태, 변경이 발생할 경우 문제가 발생한다.

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 오로지 interface에 만 의존한다. 추상화에만 의존하므로 DIP를 수행
    private final MemberRepository memberRepository;

    // 생성자 추가
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member){
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId){
        return memberRepository.findById(memberId);
    }
}
