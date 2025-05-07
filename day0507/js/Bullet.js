/* 현실의 총알을 정의한다 
java, c#, python 모든 클래스안에는 단 2가지 작성
변수(상태)와 함수(동작이나 상태 변화코드)
*/
class Bullet {
  //아래의 생성자 메서드는 new 연산자 뒤에서 호출됨
  //예) new Bullet() 방식으로
  constructor(bg, y) {
    //총알이 보유할 변수(속성 = property) 선언
    this.div = document.createElement("div");
    this.x = 0;
    this.y = y;
    this.velX = 10;
    this.bg = bg;

    //총알이 어떤 모습으로 보여질지 개성을 결정..
    this.div.style.width = 20 + "px";
    this.div.style.height = 20 + "px";
    this.div.style.borderRadius = 50 + "%";
    this.div.style.backgroundColor = this.bg;
    this.div.style.position = "absolute";
    this.div.style.left = this.x + "px";
    this.div.style.top = this.y + "px";

    document.body.appendChild(this.div);

    console.log("저 태어났어요");
  }
  // 총알의 상태를 변경시키기 위한, 즉 움직임을 표현한 메서드 정의
  move() {
    this.x += this.velX;
    this.div.style.left = this.x + "px";
  }
}
