package hello.core.member;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryMemberRepository implements MemberRepository{
    
    private static Map<Long, Member> store = new ConcurrentHashMap<>(); // 동시성 이슈가 있을 수 있기 때문에 ConcurrentHashMap<>을 사용함.

    @Override
    public void save(Member member){
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId){
        return store.get(memberId);
    }
}
