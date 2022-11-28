package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import java.util.List;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트는 순서대로 동작하지 않음.
    // 테스트가 끝나면 데이터는 깔끔히 삭제를 해주어야 함
    // TDD : 테스트주도개발, 테스트케이스를 먼저 만들고 구현클래스를 만드는 것
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("테스트이름");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("테스트이름1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("테스트이름2");
        repository.save(member2);

        Member result = repository.findByName("테스트이름2").get();
        assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("테스트이름1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("테스트이름2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}

