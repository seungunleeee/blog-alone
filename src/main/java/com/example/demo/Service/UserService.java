package com.example.demo.Service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.User;
import com.example.demo.domain.Comment.ChildComment;
import com.example.demo.handler.excption.CustomException;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.ChildCommentrepository;
import com.example.demo.repository.Commentrepository;
import com.example.demo.repository.UserRepository;

import groovyjarjarantlr4.v4.parse.ANTLRParser.throwsSpec_return;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserService {
	private  final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final Commentrepository commentRepository;
	private final ChildCommentrepository childcommentRepository;
	private final ArticleRepository articleRepository;
	
	
	@Transactional
	public User 회원가입(User user) {
		String rawpassword =user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawpassword);
		
		user.setPassword(encPassword);
		User userentity  =userRepository.save(user);
		
		return userentity;
	}
	
	@Transactional
	public User 회원정보수정(int principalid ,User user) {
		User originalinfo=new User();
		
		originalinfo =userRepository.findById(principalid).orElseThrow(()->{
			
			
			throw new CustomException("계정이 없습니다.");
		});
		
		originalinfo.setUsername(user.getUsername());
		originalinfo.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		originalinfo.setName((user.getName()));
		originalinfo.setEmail(user.getEmail());
		
		
		return originalinfo;
	}
	
	@Transactional
	public void 회원탈퇴(int principalid) {
		
		User user=userRepository.findById(principalid).orElseThrow(()->{
			
			throw new CustomException("삭제할 유저가 없습니다.");
		});
		
		List <Integer> childcommentIds= childcommentRepository.getchildcommentsID(principalid);
		childcommentRepository.deleteAllById(childcommentIds);
		List <Integer> commentids=commentRepository.getcommentsID(principalid);
		commentRepository.deleteByIdIn(commentids);
		List <Integer>articleids =articleRepository.getarticlesID(principalid);
		articleRepository.deleteAllById(articleids);
		userRepository.deleteById(principalid);
		System.out.println("삭제완료");
		
	}
	
	
	
	
	
	
}
