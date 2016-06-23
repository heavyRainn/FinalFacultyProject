package by.epam.t7.service;

import java.util.List;

import by.epam.t7.dao.FeedbackDao;
import by.epam.t7.dao.factory.MysqlDaoFactory;
import by.epam.t7.entity.Feedback;
import by.epam.t7.exception.DaoException;
import by.epam.t7.exception.ServiceException;

public class FeedbackService {

	private volatile static FeedbackService instance = null;

	public static FeedbackService getInstance() {
		if (instance == null) {
			synchronized (FeedbackService.class) {
				if (instance == null) {
					instance = new FeedbackService();
				}
			}
		}
		return instance;
	}

	public boolean signUpFeedback(Feedback feedback) throws ServiceException {
		boolean result = false;
		FeedbackDao feedbackDao;
		try {
			feedbackDao = (FeedbackDao) MysqlDaoFactory.getInstance().getDao(Feedback.class);
			result = feedbackDao.signUpFeedback(feedback);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return result;

	}

	public List<Feedback> getAllUsersFeedbacks(int id) throws ServiceException {
		List<Feedback> feedbacks = null;
		FeedbackDao feedbackDao;
		try {
			feedbackDao = (FeedbackDao) MysqlDaoFactory.getInstance().getDao(Feedback.class);
			feedbacks = feedbackDao.getAllUsersFeedbacks(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return feedbacks;
	}

	public List<Feedback> getAllFacultyFeedbacks(int id) throws ServiceException {
		List<Feedback> feedbacks = null;
		FeedbackDao feedbackDao;
		try {
			feedbackDao = (FeedbackDao) MysqlDaoFactory.getInstance().getDao(Feedback.class);
			feedbacks = feedbackDao.getAllFacultyFeedbacks(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

		return feedbacks;
	}

	public boolean delete(int id) throws ServiceException {
		boolean result = false;
		FeedbackDao feedbackDao;
		try {
			feedbackDao = (FeedbackDao) MysqlDaoFactory.getInstance().getDao(Feedback.class);
			result = feedbackDao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return result;
	}

}
