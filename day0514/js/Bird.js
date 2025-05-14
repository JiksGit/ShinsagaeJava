/* 모든 새들의 어버이 격인 그냥 새를 정의 */
class Bird {
  constructor(color, age) {
    this.color = color;
    this.age = age;
    console.log("Bird의 생성자 호출 됨");
  }

  eat() {
    console.log("새가 머기를 머거요");
  }
  fly() {
    console.log("새가 나라요");
  }
  getAge() {
    console.log(this.age + " 내 나이 ");
  }
}
