class Enemy extends GameObject {
  constructor(container, src, x, y, width, height, velX, velY) {
    super(container, src, x, y, width, height, velX, velY);
  }

  tick() {
    this.x -= this.velX;
  }
  render() {
    this.img.style.left = this.x + "px";

    if (this.x < -this.width) {
      this.removeObject(enemyArray, this);
    }
  }
}
