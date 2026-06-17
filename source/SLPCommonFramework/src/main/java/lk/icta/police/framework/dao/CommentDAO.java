package lk.icta.police.framework.dao;

import lk.icta.police.framework.constant.DBConstant;
import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.constant.PoliceEnumConstant;
import lk.icta.police.framework.database.DatabaseManager;
import lk.icta.police.framework.vo.CommentsVO;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    private static CommentDAO instance = null;
    private static final Logger LOGGER = Logger.getLogger(CommentDAO.class);

    public static CommentDAO getInstance() {
        synchronized (CommentDAO.class) {
            if (instance == null) {
                instance = new CommentDAO();
            }
            return instance;
        }
    }

    private CommentDAO() {

    }

    public List<CommentsVO> getCommentList(Connection conn, long applicationId) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<CommentsVO> commentsVOs = new ArrayList<CommentsVO>();
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_COMMENTS_LIST_BY_APPLICATION_ID);
            pstm.setLong(1, applicationId);
            rst = pstm.executeQuery();


            while (rst.next()) {
                commentsVOs.add(getConstructedCommentsVOFromResultSet(rst));
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return commentsVOs;
    }

    private CommentsVO getConstructedCommentsVOFromResultSet(ResultSet rst) throws SQLException {
        CommentsVO commentsVO = new CommentsVO();
        commentsVO.setAddressId(rst.getLong(DBConstant.COMMENTS_COL.ADDRESS_ID));
        commentsVO.setApplicationId(rst.getLong(DBConstant.COMMENTS_COL.APPLICATION_ID));
        commentsVO.setComment(rst.getString(DBConstant.COMMENTS_COL.COMMENT));
        commentsVO.setCommentId(rst.getLong(DBConstant.COMMENTS_COL.COMMENT_ID));
        commentsVO.setCommentType(rst.getString(DBConstant.COMMENTS_COL.COMMENT_TYPE));
        commentsVO.setCreatedDateTime(rst.getTimestamp(DBConstant.COMMENTS_COL.CREATED_DATE_TIME));
        commentsVO.setCreatedUserId(rst.getInt(DBConstant.COMMENTS_COL.CREATED_USER_ID));
        commentsVO.setCreatedUserName(rst.getString(DBConstant.COMMENTS_COL.CREATED_USER_NAME));
        commentsVO.setCreatedUserRole(rst.getInt(DBConstant.COMMENTS_COL.CREATED_USER_ROLE));
        return commentsVO;
    }

    public long addComments(Connection conn, CommentsVO commentsVO) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.ADD_COMMENT, PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setString(1, commentsVO.getComment());
            pstm.setInt(2, commentsVO.getCreatedUserId());
            pstm.setString(3, commentsVO.getCreatedUserName());
            pstm.setInt(4, commentsVO.getCreatedUserRole());
            pstm.setString(5, commentsVO.getCommentType());
            pstm.setLong(6, commentsVO.getApplicationId());
            if (commentsVO.getAddressId() <= 0) {
                pstm.setNull(7, Types.INTEGER);
            } else {
                pstm.setLong(7, commentsVO.getAddressId());
            }


            int result = pstm.executeUpdate();
            long commentId = 0l;
            if (result > 0) {
                rs = pstm.getGeneratedKeys();
                while (rs.next()) {
                    commentId = rs.getInt(1);
                }
				
            }

            commentsVO.setCommentId(commentId);
            DatabaseManager.close(result);
			
			rs.close();
            pstm.close();
            return commentId;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rs);
            DatabaseManager.close(pstm);
        }
        return 0;
    }

    public List<CommentsVO> getCommentListByApplicationId(long applicationId, Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<CommentsVO> commentsVOs = new ArrayList<CommentsVO>();
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_COMMENTS_LIST_BY_APPLICATION_ID);
            pstm.setLong(1, applicationId);
            rst = pstm.executeQuery();
            while (rst.next()) {
                commentsVOs.add(getConstructedCommentsVOFromResultSet(rst));
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return commentsVOs;
    }


    public List<CommentsVO> getCommentListByAddressId(long addressId, Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<CommentsVO> commentsVOs = new ArrayList<CommentsVO>();
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_COMMENTS_LIST_BY_ADDRESS_ID);
            pstm.setLong(1, addressId);
            rst = pstm.executeQuery();

            while (rst.next()) {
                commentsVOs.add(getConstructedCommentsVOFromResultSet(rst));
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return commentsVOs;
    }


    public List<CommentsVO> getCommentListByTypeAndApplicationId(long applicationId, String commentType, Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<CommentsVO> commentsVOs = new ArrayList<CommentsVO>();
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_COMMENTS_LIST_BY_APPLICATION_ID_AND_TYPE);
            pstm.setString(1, commentType);
            pstm.setLong(2, applicationId);
            rst = pstm.executeQuery();

            while (rst.next()) {
                commentsVOs.add(getConstructedCommentsVOFromResultSet(rst));
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return commentsVOs;
    }

    public List<CommentsVO> getCommentListByTypeAndAddressId(long addressId, String commentType, Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<CommentsVO> commentsVOs = new ArrayList<CommentsVO>();
        try {
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_COMMENTS_LIST_BY_ADDRESS_ID_AND_TYPE);
            pstm.setString(1, commentType);
            pstm.setLong(2, addressId);
            rst = pstm.executeQuery();

            while (rst.next()) {
                commentsVOs.add(getConstructedCommentsVOFromResultSet(rst));
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return commentsVOs;
    }

    public CommentsVO getCommentById(long commentId, Connection con) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        CommentsVO commentsVO = null;
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.SELECT_COMMENT_BY_ID);
            pstm.setLong(1, commentId);
            rst = pstm.executeQuery();
            while (rst.next()) {
                commentsVO = getConstructedCommentsVOFromResultSet(rst);
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return commentsVO;
    }

    public String updateComment(CommentsVO comment, Connection con) {
        PreparedStatement pstm = null;
//        ResultSet rst = null;
        String status = PoliceConstant.ERROR;
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_COMMENT_BY_ID);
            pstm.setString(1, comment.getComment());
            pstm.setString(2, comment.getCommentType());
            pstm.setLong(3, comment.getCommentId());

            int result = pstm.executeUpdate();

            if (result > 0) {
                status = PoliceConstant.SUCCESS;
            }
//			rst.close();
            pstm.close();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
//DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return status;
    }

    public String updateCommentByTypeAndApplicationId(long applicationId, String letterContent, String commentType, Connection con) {
        PreparedStatement pstm = null;
  //      ResultSet rst = null;
        String status = PoliceConstant.ERROR;
        try {
            pstm = con.prepareStatement(DBConstant.QUERY.UPDATE_COMMENT_BY_TYPE_AND_APPLICATION_ID);
            pstm.setString(1, letterContent);
            pstm.setString(2, commentType);
            pstm.setLong(3, applicationId);

            int result = pstm.executeUpdate();

            if (result > 0) {
                status = PoliceConstant.SUCCESS;
            }
//			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
 //           DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
        }
        return status;
    }

    public List<String> findDhaCommentsByApplicationId(long applicationId) {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<String> dhaComments = new ArrayList<String>();
        Connection conn = null;
        try {
            conn = DatabaseManager.getPOLDBConnection();
            pstm = conn.prepareStatement(DBConstant.QUERY.SELECT_COMMENTS_LIST_BY_APPLICATION_ID_AND_TYPE);
            pstm.setString(1, PoliceEnumConstant.CommentType.DHA.toString());
            pstm.setLong(2, applicationId);
            rst = pstm.executeQuery();

            while (rst.next()) {
                dhaComments.add(rst.getString(DBConstant.COMMENTS_COL.COMMENT));
            }
			rst.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.close(rst);
            DatabaseManager.close(pstm);
            DatabaseManager.close(conn);
        }
        return dhaComments;
    }

}
