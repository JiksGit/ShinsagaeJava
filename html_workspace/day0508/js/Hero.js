/* 주인공을 정의한다 */
class Hero {
  //ES6의 클래스는 멤버변수를 생성자에 안에 둬야 함
  constructor(container, x, y, width, height, velX, velY) {
    // 외부에서 전달된 데이터를 나의 객체에 보관하는 기법을 injection
    this.container = container;
    this.img = document.createElement("img");
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.velX = velX;
    this.velY = velY;

    this.imgArray = [];
    this.n = 0;
    for (let i = 1; i <= 18; i++) {
      this.imgArray.push(`../../images/hero/image${i}.png`);
    }

    //style
    this.img.src = "../../images/hero/image1.png";
    this.img.style.position = "absolute";
    this.img.style.left = this.x + "px";
    this.img.style.top = this.y + "px";

    this.img.style.width = this.width + "px";
    this.img.style.height = this.height + "px";

    this.container.appendChild(this.img); //컨테이너에 부착
    //움직이기 시작
    this.doIdle();
  }

  // 주인공 펄럭임 동작
  // 게임루프와 상관없이 자체적으로 끝없는 루프로 움직임 표현
  doIdle() {
    if (this.n >= 17) this.n = 0;
    this.n++;
    this.img.src = this.imgArray[this.n];
    // setTimeout(this.doIdle(), 10);
    setTimeout(() => {
      // 화살표 함수는 this를 가질 수 없ㅇ므로, 여기서 this는 상위 스코프를 나타냄
      this.doIdle();
    }, 32);
  }

  // 모든 방향에 대한 움직임 동작 정의
  move() {
    this.x += this.velX;
    this.y += this.velY;
    // 변화된 물리량을 화면에 반영(rendering)
    this.img.style.left = this.x + "px";
    this.img.style.top = this.y + "px";
  }
}
