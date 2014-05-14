package org.yangchigi.repository;

public class SingletonRepository {
	private static UserRepository userRepository = new UserRepository();
	private static IdeaRepository ideaRepository = new IdeaRepository();
	private static TodayRepository todayRepository = new TodayRepository();
	private static LikeRepository likeRepository = new LikeRepository();
	private static CommentRepository commentRepository = new CommentRepository();
	
	public static TodayRepository getTodayRepository() {
		return todayRepository;
	}

	public static UserRepository getUserRepository() {
		return userRepository;
	}

	public static IdeaRepository getIdeaRepository() {
		return ideaRepository;
	}

	public static LikeRepository getLikeRepository() {
		return likeRepository;
	}

	public static CommentRepository getCommentRepository() {
		return commentRepository;
	}
}
