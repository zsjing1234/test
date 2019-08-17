package test;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.swing.internal.plaf.basic.resources.basic;
import com.surpass.realkits.JGecService;
import com.surpass.realkits.exception.GecException;

import test.DBUtil;
import test.History;

public class Test extends HttpServlet {
	
//	public void service(HttpServletRequest request, HttpServletResponse response){
//		try {
//			JGecService gec = null;
//			gec = DBUtil.gec();
//			List<String> s1 = gec.DBECEnumServerName();
//			
//			for (int i = 0; i < s1.size(); i++) {
//				String lp = s1.get(i);
//				List<String> l = gec.DBECEnumDeviceName(lp);
//				List<Long> l2 = gec.DBECEnumTagID(lp);
//				System.out.println("����������"+lp);
//				String s = "���еģ�";
//				for (int j = 0; j<l.size(); j++) {
//					System.out.println("�豸���ƣ�"+l.get(j)+"::"+l.get(j).equals("BHG_SHCL")+":"+l.get(j).length());
//					List<Long> lo = null;
//					
//						try {
//							lo = gec.DBECEnumTagIDOfDeviceByDeviceName(lp, l.get(j));
//						} catch (GecException e) {
//							continue;
//						}
//					System.out.println("�豸���ƣ�"+l.get(j)+"�豸����"+l.size()+"�豸��id��"+lo.size());
//					if (!(lo.size()<=0)) {
//						for (int j2 = 0; j2 <=1; j2++) {
//							System.out.println(lo.get(j2)+"::�豸��id"+j2);
//						}
//					}
//					
//					
//				}
//			}
//			double d = gec.DBECGetTagRealField(s1.get(0), 1, "FN_RTVALUE");
//			request.setAttribute("data",d);
//			request.getRequestDispatcher("test.jsp").forward(request, response);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
	public void service(HttpServletRequest request, HttpServletResponse response){
		String date = request.getParameter("date");
		String date2 = request.getParameter("date2");
		DateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		List<History> list = new ArrayList<History>();
		JGecService gec = null;
		int num = 2;
		String i = "1847,1846";
		String []ida = i.split(",");
		List<Integer> idb = new ArrayList<Integer>();

		for (int b = 0; b < ida.length; b++) {// �������ÿ�����鲢��ת������������
			idb.add(Integer.parseInt(ida[b]));
		}
		try {
			Date d1 = d.parse(date);
			Date d2 = d.parse(date2);
			gec = DBUtil.gec();
			String ser = gec.DBECEnumServerName().get(0);// ��÷���������
			List<Double> dous = new ArrayList<Double>();// ��õ���ʷ����ֵ���洦
			List<Long> strs = new ArrayList<Long>();// ��õ���ʷ���ݴ��漯��ʱ�䴦
			
			List<Double> dous2 = new ArrayList<Double>();// ��õ���ʷ����ֵ���洦
			List<Long> strs2 = new ArrayList<Long>();// ��õ���ʷ���ݴ��漯��ʱ�䴦
			
			for (int a = 0; a < idb.size(); a++) {// ������ü�����id
				if (a>=1) {
					dous2 = dous;
					strs2 = strs;
					dous = new ArrayList<Double>();
					strs = new ArrayList<Long>();
				}
				     
				String tag = gec.DBECGetTagName(ser, idb.get(a)).trim();// ��ö�Ӧid�ĺ�λ��
				System.out.println("λ��"+tag);
				gec.DBECGetTagRealHistory(ser,tag,idb.get(a),
						d1.getTime() / 1000,
						d2.getTime() / 1000, dous, 5000, strs);// ��ö�Ӧ����ʷ����
				System.out.println(dous.size()+"dou");
					System.out.println(tag);
//					if (strs.size() >= 1 && dous.size() >= 1) {// ȷ����ʷ���ݵļ���ֵ��ֵ
//					History h = new History();
//					h.setId(tag.trim());// �����λ��
//					h.setVal(dous);// ����ֵ����
//					h.setArr(strs);// ��ʱ��     
//					list.add(h);// ����Ž�ȥ
//				}

			}
			List<String2> st2 = new ArrayList<String2>();
			if (strs.size()>strs2.size()) {
				for (int j = 0; j < strs.size(); j++) {
					for (int j2 = 0; j2 < strs2.size(); j2++) {
						long l = strs.get(j);
						long l2 = strs2.get(j2);
						System.out.println(strs.get(j)+"::"+strs2.get(j2)+"::"+(l==l2)+"�Ա�");
						if (l==l2) {
							String2 a = new String2();
							DecimalFormat df = new DecimalFormat("#.00");
							
							a.setAa(Double.parseDouble(df.format(dous.get(j))));
							a.setBa(Double.parseDouble(df.format(dous2.get(j2))));
							st2.add(a);
							break;
						}else {
							if (!(j2 < strs2.size())) {
								String2 a = new String2();
								a.setAa(new Double(0));
								a.setBa(new Double(0));
							}
						}
					}
				}
			}else {
				for (int j = 0; j < strs2.size(); j++) {
					for (int j2 = 0; j2 < strs.size(); j2++) {
						long l = strs2.get(j);
						long l2 = strs.get(j2);
						System.out.println(strs.get(j2)+"::"+strs2.get(j)+"::"+(l==l2)+"�Ա�");
						if (l==l2) {
							String2 a = new String2();
							DecimalFormat df = new DecimalFormat("#.00");
							
							a.setAa(Double.parseDouble(df.format(dous.get(j2))));
							a.setBa(Double.parseDouble(df.format(dous2.get(j))));
							st2.add(a);
							break;
						}else {
							if (!(j2 < strs.size())) {
								String2 a = new String2();
								a.setAa(new Double(0));
								a.setBa(new Double(0));
							}
						}
					}
				}
			}
			System.out.println(strs2.size()+"::"+strs.size());
//			List<String> data = new ArrayList<String>();
//			SimpleDateFormat sdFormatter = new SimpleDateFormat(
//					"yyyy-MM-dd HH:mm:dd");// ʱ���ʽ
//			int[] e = { 0, 0, 0 };// ͨ������ѡ��ʱ�������һ����Ϊʱ���
//			for (int i2 = 0; i2 < list.size(); i2++) {
//				e[0] = list.get(i2).getArr().size();
//				if (e[1] < e[0]) {
//					e[1] = e[0];
//					e[2] = i2;
//				}
//			}
			request.setAttribute("l", st2);
			/*List<Double> l = new ArrayList<Double>();
			System.out.println(e[2]);
			uuuuuuuuuuui List<Long> arr = list.get(e[2]).getArr();// ������������ʱ����뼯��
			
			int num2 = arr.size();// �ó�ʱ�����
			System.out.println(num + ":::" + list.size());
			for (int i2 = 0; i2 < num2; i2++) {// ����ʱ�����ʱ���ʽ����
				Date nowTime = new Date(arr.get(i2) * 1000);
				String da = sdFormatter.format(nowTime);
				DecimalFormat df = new DecimalFormat("#.00");
				l.add(Double.parseDouble(df.format(dous.get(i2))));
				
				data.add("'" + da + "'");// ����
			}
			System.out.println(l);
			request.setAttribute("list", l);
			request.setAttribute("data", data);*/
			System.out.println(date);
			request.getRequestDispatcher("test.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}

