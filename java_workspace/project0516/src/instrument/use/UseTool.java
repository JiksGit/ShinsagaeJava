package instrument.use;

import instrument.Drum;
import instrument.Piano;
import instrument.MusicTool;
import riding.Roller;

// 납품을 앞두고 모든 악기에 대해 최종 테스트
public class UseTool{
	public static void main(String[] args) 
	{
		
		MusicTool p = new Piano();
		p.setVolume();
		
		
		Roller p2 = new Piano();
		p2.roll();
		
		MusicTool d = new Drum();
		d.setVolume();
		
		// 오케스트라 협주... 모든 악기를 한꺼번에 볼륨을 조절
		// 각 악기가 보유한 메서드가 무엇인지 알수가 없다.
		// 메서드 명의 일관성 부재... 각 개발자에게 부탁
		// 기술적으로 개발자들에게 업무 규칙을 만들어 제재를 가해야함
		// 따라서 가이드 라인 제시. 
		
		//Violin violin;
	
	}
}
