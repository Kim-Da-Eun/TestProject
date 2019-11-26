package findwatson.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import findwatson.admin.dto.ExpertDTO;
import findwatson.admin.dto.HospitalListDTO;
import findwatson.admin.dto.NoticeDTO;

public class ManagerDAO {
	private static ManagerDAO instance;
	private BasicDataSource bds = new BasicDataSource();
	//DBCP
	private ManagerDAO () {
		bds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		bds.setUsername("manager");
		bds.setPassword("manager");
		bds.setInitialSize(30);
	}
	public synchronized static ManagerDAO getInstance() {
		if(instance == null){									
			instance = new ManagerDAO();
		}
		return instance;
	}
	//Connection
	private Connection getConnection() throws Exception{
		return bds.getConnection();
	}
	//병원 추가
	public int insertHospital(HospitalListDTO dto) throws Exception{
		String sql = "insert into hosptList values(hospital_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?)";
		try(
				Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, dto.getHosptName());
			pstat.setInt(2, dto.getZipcode());
			pstat.setString(3, dto.getCity());
			pstat.setString(4, dto.getGu());
			pstat.setString(5, dto.getDong());
			pstat.setString(6, dto.getAddress());
			pstat.setString(7, dto.getPhone());
			pstat.setString(8, dto.getHomepage());
			pstat.setString(9, dto.getImg());
			pstat.setString(10, dto.getMedicalAnimal());
			pstat.setString(11, dto.getMedicalDept());
			pstat.setString(12, dto.getInfoRegist());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	//병원정보 수정
	public int modifyHospital (HospitalListDTO dto, int seq)throws Exception {
		String sql = "update hosptList set HosptName=?,zipcode=?,city=?,gu=?,dong=?,address=?,phone=?,homepage=?,img=?,medicalAnimal=?,medicalDept=?,infoRegist=? where seq=?";
		try(
				Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, dto.getHosptName());
			pstat.setInt(2, dto.getZipcode());
			pstat.setString(3, dto.getCity());
			pstat.setString(4, dto.getGu());
			pstat.setString(5, dto.getDong());
			pstat.setString(6, dto.getAddress());
			pstat.setString(7, dto.getPhone());
			pstat.setString(8, dto.getHomepage());
			pstat.setString(9, dto.getImg());
			pstat.setString(10, dto.getMedicalAnimal());
			pstat.setString(11, dto.getMedicalDept());
			pstat.setString(12, dto.getInfoRegist());
			pstat.setInt(13, seq);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	//병원정보 삭제
	public int delHospital(int seq) throws Exception{
		String sql = "delete hosptList where seq = ?";
		try(
				Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	//공지사항 쓰기
	public int writeNotice(NoticeDTO dto) throws Exception{
		String sql = "insert into notice values(notice_seq.nextval,?,?,?,?)";
		try(
				Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getContent());
			pstat.setTimestamp(3, dto.getWriteDate());
			pstat.setInt(4, dto.getViewCount());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	//공지사항 수정
	public int modifyNotice (NoticeDTO dto, int seq)throws Exception {
		String sql = "update notice set title=?,content=? where seq=?";
		try(
				Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getContent());
			pstat.setInt(3, seq);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	//공지사항 삭제
	public int delNotice(int seq) throws Exception {
		String sql = "delete from notice where seq=?";
		try(
				Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	//1:1문의 게시글 삭제
	public int delOneByOne(int seq) throws Exception {
		String sql = "delete from OneByOne where seq=?";
		try(
				Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	//전문가 Q&A 글쓰기
	public int writeExpert(ExpertDTO dto) throws Exception{
		String sql = "insert into Expert values(expert_seq.nextval,?,?,?,?,?)";
		try(
				Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, dto.getWriter());
			pstat.setString(2, dto.getTitle());
			pstat.setString(3, dto.getContent());
			pstat.setTimestamp(4, dto.getWriteDate());
			pstat.setInt(5, dto.getViewCount());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	//전문가 Q&A 수정
	public int modifyExpert (ExpertDTO dto, int seq)throws Exception {
		String sql = "update Expert set title=?,content=? where seq=?";
		try(
				Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getContent());
			pstat.setInt(3, seq);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	//전문가 Q&A 삭제
	public int delExpert(int seq) throws Exception {
		String sql = "delete from Expert where seq=?";
		try(
				Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	//
}
