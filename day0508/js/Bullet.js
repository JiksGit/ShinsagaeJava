class Bullet {
  constructor(container, x, y, width, height, velX, velY, bg) {
    this.container = container;
    this.div = document.createElement("div");
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.velX = velX;
    this.velY = velY;
    this.bg = bg;

    //style
    this.div.style.background = this.bg;

    this.div.style.position = "absolute";
    this.div.style.left = this.x + "px";
    this.div.style.top = this.y + "px";

    this.div.style.width = this.width + "px";
    this.div.style.height = this.height + "px";
    this.div.style.borderRadius = 50 + "%";
    //총알의 테두리에 블러 효과 주기
    this.div.style.filter = "blur(2px)"; // 퍼짐 정보를 숫자로 표현
    this.container.appendChild(this.div);
  }

  move() {
    // 만일 총알이 적군등에 맞지 않고 화면 밖으로 나갈 시 메모리 관리를 위해
    // 제거 ( 화면 제거 + 배열 제거 )
    if (this.x >= 1850) {
      this.container.removeChild(this.div); // 화면에서 제거
      if (this.bg == "red") {
        bulletArray.splice(bulletArray.indexOf(this), 1);
      } else {
        yellowbulArray.splice(yellowbulArray.indexOf(this), 1);
      }
    }
    this.x += this.velX;
    this.div.style.left = this.x + "px";
  }
}
