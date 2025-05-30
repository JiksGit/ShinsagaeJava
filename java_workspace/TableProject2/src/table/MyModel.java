package table;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/*
 * JTable 개발 분야에서 전해내려오는 많이 알려진 개발 방법(=패턴) 중 하나인 MVC 패턴을 구현한
 * 컴포넌트이다. 하지만 완벽하진 않음
 * 왜??   MC의 역할을 동시에 수행하므로... 데이터를 보유할 뿐만 아니라, 그 데이터를 디자인 영역에
 * 반영하는 코드도 포함되어 있기 때문에... 
 * 결론) 중요한 것은 JTable과 데이터를 분리시켜놓은 기술임
 */

// TableModelListener 란? 모델이 데이터를 유저가 수정할 때 발생되는 이벤트를 감지하는 리스너
public class MyModel extends AbstractTableModel implements TableModelListener{
	
	// 회원 정보 (층, 호를 표현하기 위한 이차원 배열 형태의 데이터가 필요)
	String[][] rows = new String[100][4];
	String[] columns = {
			"ID","NAME","TEL"
	};
	
	MemberRegist memberRegist; // 제어하기 때문에 주소값을 보유하기 위한 has a 관계 선언
	
	public MyModel(MemberRegist memberRegist) {
		// 모델과 리스너 연결
		this.addTableModelListener(this); // 나의 레코드가 변경될 때 그것을 감지하겠다!
		this.memberRegist = memberRegist;
	}

	@Override
	public int getRowCount() {
		return rows.length;
	}
	@Override
	public int getColumnCount() {
		return columns.length;
	}
	
	// 아래의 메서드는 컬럼 수만큼 반복하면서 호출되는데, 이때 매개변수로 넘겨받는 column의 값은 자동 증가하면서
	// 전달되어 진다..
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}
	// getValueAt() 메서드는 getRowCount() X getColumnCount() 수만틈 호출하면서
	// 표를 이루는 각 셀(행,열)의 좌표마다 어떠한 값을 넣을지 return 이 결정한다
	@Override 
	public Object getValueAt(int rowIndex, int columnIndex) {
		return rows[rowIndex][columnIndex];
	}
	
	// 셀에서 원하는 데이터 K를 입력하고, 엔터를 치는 순간, 해당 셀의 row, col, k 값이 전달됨
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
//		System.out.println("당신은 " + rowIndex + ", " + columnIndex + "의 데이터를" + value +"로 바꾸길 원하죠?");
		// 모델이 이차원 배열에 반영하기
		rows[rowIndex][columnIndex] = (String) value;
		
		memberRegist.edit(rows[rowIndex]);
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
//		boolean flag = (columnIndex == 0) ? false : true;
		return (columnIndex == 0) ? false : true;
//		if(flag) return false;
//		else return true;
	}
	
	// 테이블의 한 셀을 수정후 엔터치는 순간 이 메서드가 호출됨
	@Override
	public void tableChanged(TableModelEvent e) {
		System.out.println("편집 했어?");
	}
}
