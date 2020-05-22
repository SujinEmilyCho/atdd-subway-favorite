package wooteco.subway.web.member;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import wooteco.subway.domain.member.Member;
import wooteco.subway.service.member.MemberService;
import wooteco.subway.service.member.dto.MemberRequest;
import wooteco.subway.service.member.dto.MemberResponse;
import wooteco.subway.service.member.dto.UpdateMemberRequest;

@RestController
public class MemberController {
	private MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping("/members")
	public ResponseEntity<Void> createMember(@RequestBody MemberRequest view) {
		Member member = memberService.createMember(view.toMember());
		return ResponseEntity
			.created(URI.create("/members/" + member.getId()))
			.build();
	}

	@GetMapping("/members")
	public ResponseEntity<MemberResponse> getMember(@LoginMember Member member) {
		return ResponseEntity.ok().body(MemberResponse.of(member));
	}

	@PutMapping("/members")
	public ResponseEntity<MemberResponse> updateMember(@LoginMember Member member,
		@RequestBody UpdateMemberRequest updateMemberRequest) {
		Member updatedMember = memberService.updateMemberByUser(member, updateMemberRequest);
		return ResponseEntity.ok().body(MemberResponse.of(updatedMember));
	}

	@DeleteMapping("/members")
	public ResponseEntity<Void> deleteMember(@LoginMember Member member) {
		memberService.deleteMemberByUser(member);
		return ResponseEntity.noContent().build();
	}
}
