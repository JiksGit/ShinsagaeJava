/* 현실의 공을 정의한다 */
class Ball {
  //이 공이 태어날 때 부여하고 싶은 특징들을 여기서 결정
  constructor(container, x, y, width, height, velX, velY, bg, flagX, flagY) {
    this.container = container; //이 공을 어디에 붙일 지 결정..
    this.div = document.createElement("div");

    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.velX = velX; //x축으로의 속도
    this.velY = velY; //y축으로의 속도
    this.bg = bg; //공의 색상

    this.flagX = flagX;
    this.flagY = flagY;

    //style
    this.div.style.position = "absolute";
    this.div.style.left = this.x + "px";
    this.div.style.top = this.y + "px";

    this.div.style.width = this.width + "px";
    this.div.style.height = this.height + "px";
    this.div.style.backgroundColor = this.bg;
    this.div.style.borderRadius = 50 + "%";

    // 넘겨받을 대상 컨테이너가 document.body일 수도 있고, wrapper 일 수도
    // 있다, 즉 결정되어 있지 않음.. 개발자가 호출 시 결정해야함
    this.container.appendChild(this.div);
  }
  move() {
    if (this.x + this.width + this.velX >= 600 || this.x - this.velX <= 0) {
      this.flagX = !this.flagX;
    }
    if (this.y + this.height + this.velY >= 600 || this.y - this.velY <= 0) {
      this.flagY = !this.flagY;
    }
    if (this.flagX) {
      this.x += this.velX;
    } else {
      this.x -= this.velX;
    }
    if (this.flagY) {
      this.y += this.velY;
    } else {
      this.y -= this.velY;
    }
    this.div.style.left = this.x + "px";
    this.div.style.top = this.y + "px";
  }
}
