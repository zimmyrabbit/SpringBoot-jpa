package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

	@Autowired MemberRepository memberRepository;
	
	@Test
	@Transactional
	@Rollback(value=false)
	public void testMember() throws Exception {
		//given
		MemberTest member = new MemberTest();
		member.setUsername("memberA");
		
		//when
		Long savedId = memberRepository.save(member);
		MemberTest findMember = memberRepository.find(savedId);
		
		//then
		Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
		Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
		Assertions.assertThat(findMember).isEqualTo(member);
	}
	
	
}
