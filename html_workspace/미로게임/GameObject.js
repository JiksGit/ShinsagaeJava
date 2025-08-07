class GameObject{
    constructor(container, x, y, width, height, velX, velY, bg, border, borderColor){
        this.container = container;
        this.div = document.createElement("div"); // 게임 완성 후 이미지를 백그라운드로 적용
        // 이미지로 개발하지 못하는 이유? - 이미지는 부모 기능이 없음
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = velX;
        this.velY = velY;
        this.bg = bg;
        this.border = border;
        this.borderColor = borderColor;
        
        this.div.style.position = "absolute";
        this.div.style.left = this.x +"px";
        this.div.style.top = this.y +"px";
        this.div.style.width = this.width + "px";
        this.div.style.height = this.height + "px";
        this.div.style.background = this.bg;
        this.div.style.border = `${this.border}px solid ${this.borderColor}`;

        this.container.appendChild(this.div);
    }
    // 오브젝트의 물리량 계산 update() tick()
    tick() {
        
    }
    // 변화된 물리량을 화면에 반영하는 그래픽 작업 render(), paint()
    render() {

    }
}