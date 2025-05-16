/* 단어를 정의한다 */
class Word {
  constructor(container, x, y, text, color) {
    this.container = container;
    // 왜 span? span은 인라인이므로, 너비가 contents만큼 확보(wrapping)
    this.span = document.createElement("span");
    this.x = x;
    this.y = y;
    this.text = text; // 이 span 포함하게 될 단어
    this.color = color;

    this.span.style.display = "inline-block";
    this.span.style.position = "absolute";
    this.span.style.left = this.x + "px";
    this.span.style.top = this.y + "px";
    this.span.innerText = this.text;
    this.span.style.color = this.color;

    this.container.appendChild(this.span);
  }

  // 물리량 변화
  tick() {
    this.y += 15;
  }

  // 그래픽 처리
  render() {
    this.span.style.top = this.y + "px";
  }
}
