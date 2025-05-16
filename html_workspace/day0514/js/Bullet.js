class Bullet extends GameObject {
  constructor(container, src, x, y, width, height, velX, velY) {
    super(container, src, x, y, width, height, velX, velY);
  }
  //   // 제거 메서드
  //   removeObject(array, target) {
  //     // 1) 화면에서 제거
  //     this.container.removeChild(array[array.indexOf(target)].img);

  //     // 2) 총알이 있던 배열에서 제거
  //     array.splice(array.indexOf(target), 1);
  //   }

  tick() {
    this.x += this.velX;
  }
  render() {
    // 총알이 한걸음씩 나아갈 때마다, 총알과 적군과의 충돌을 체크해서 제거처리.
    for (let i = 0; i < enemyArray.length; i++) {
      let enemy = enemyArray[i];
      if (collisionCheck(this.img, enemy.img)) {
        // 충돌했다면.. 너죽고 나죽고
        this.removeObject(bulletArray, this);
        this.removeObject(enemyArray, enemy);
        setScore(10);
      }
    }

    this.img.style.left = this.x + "px";

    // 적군에 충돌하지 않은 총알은 자동 제거.
    if (this.x > 1200) {
      this.removeObject(bulletArray, this);
    }
  }
}
