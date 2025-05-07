class Rect {
  constructor(container, x, y, width, height, bg) {
    this.div = document.createElement("div");
    this.container = container;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.bg = bg;

    this.div.style.position = "absolute";
    this.div.style.left = this.x + "px";
    this.div.style.top = this.y + "px";

    this.div.style.width = this.width + "px";
    this.div.style.height = this.height + "px";
    this.div.style.backgroundColor = this.bg;
    this.container.appendChild(this.div);
    this.move();
  }

  move() {
    console.log("move()....");
    //여기서 막대의 크기를 감속도로 공식을 적용하여 움직여보자
    //나의 높이 = 현재 나의 높이 + a* (목표높이 - 현재 나의 높이);
    setTimeout(() => {
      this.div.style.height =
        parseFloat(this.div.style.height) +
        0.1 * (500 - parseFloat(this.div.style.height));
      this.move(); // 화살표 내에서의 this는 상위 스코프(Rect객체)
    }, 100);
  }
}
