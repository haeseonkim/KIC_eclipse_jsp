import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisEx03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String resource = "myBatisConfig.xml";
		
		// 파일 읽어 오기
		InputStream is = null;
		// 데이터베이스 연결
		SqlSession sqlSession = null;
		
		
		try {
			is = Resources.getResourceAsStream( resource );
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
			System.out.println("호출 성공");
			
			sqlSession = sqlSessionFactory.openSession();
			System.out.println("연결 성공");
			
			//DeptTO to = (DeptTO)sqlSession.selectOne("select1");
			
			//값을 넣어야 할 경우 
			//DeptTO to = (DeptTO)sqlSession.selectOne("select2", "10");
			
			
			// 여러개 값을 sql에 넣어야 할경우
			DeptTO pto = new DeptTO();
			pto.setDeptno("10");
			pto.setDname("ACCOUNTING");
			
			DeptTO to = (DeptTO)sqlSession.selectOne("mybatis.select3", pto);
			
			System.out.println(to.getDeptno());
			System.out.println(to.getDname());
			System.out.println(to.getLoc());
		} catch (IOException e) {
			System.out.println("에러" + e.getMessage());
		} finally {
			if(sqlSession != null) sqlSession.close();
			if(is != null) try { is.close(); } catch(IOException e) {}
		}
	}

}
