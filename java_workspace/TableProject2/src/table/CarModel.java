package table;

import javax.swing.table.AbstractTableModel;

/*
 * JTable 개발 분야에서 전해내려오는 많이 알려진 개발 방법(=패턴) 중 하나인 MVC 패턴을 구현한
 * 컴포넌트이다. 하지만 완벽하진 않음
 * 왜??   MC의 역할을 동시에 수행하므로... 데이터를 보유할 뿐만 아니라, 그 데이터를 디자인 영역에
 * 반영하는 코드도 포함되어 있기 때문에... 
 * 결론) 중요한 것은 JTable과 데이터를 분리시켜놓은 기술임
 */
public class CarModel extends AbstractTableModel{

	@Override
	public int getRowCount() {
		return 3;
	}
	@Override
	public int getColumnCount() {
		return 5;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return "Audi";
	}
}
