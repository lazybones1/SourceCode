# MinesFinder
교과목명 : 2019학년도 2학기 소스코드 분석   
과제 : 기존 Minesweeper 게임 코드 분석 및 기능 추가   
인원 : [김진산](https://github.com/lazybones1), [김현수](https://github.com/kimhyun5u)

---
## 추가 기능
1. 사용자 설정 게임   
    사용자에게 가로 칸, 세로 칸, 지뢰의 수를 각각 입력 받아 그에 맞게 게임 화면을 만든다. 사용자 설정 게임을 시작하면 가로 칸, 세로 칸, 지뢰의 수를 입력하는 창이 뜨고 그에 맞게 게임 화면을 만ㅍ들어 플레이 할 수 있도록 한다. 가로 칸, 세로 칸, 지로의 수에 따라 기록이 기록은 저장하지 않는다.   
    
    <img src="https://user-images.githubusercontent.com/53360337/203187506-98d48a13-2331-4747-b1d8-dd9f859db9ae.png" width="300px" height="300px">

2. 효과음 추가   
    BGM, 버튼 선택 시 효과음, 지뢰를 선택했을 경우의 효과음을 추가한다. BGM은 게임을 시작할 때 켜지고 종료 시 꺼진다. 효과음은 .wav 파일을 사용한다.

    <img src="https://user-images.githubusercontent.com/53360337/204255055-905f7053-5938-4fec-b847-b43a582997d2.png" width="150px" height="100px">


3. 클릭방법 추가   
    마우스 좌, 우 클릭을 동시에 하였을 경우 주변의 지뢰 개수와 사용자가 지뢰가 있을 것으로 판단하여 표시한 것의 개수를 비교하여 만일 같을 경우 사용자가 표시한 부분을 제외하고 주변 칸을 선택한다. 사용자가 지뢰의 위치를 잘못 추측한 경우에도 주변을 선택하기 때문에 지뢰가 터질 수 있다.

    ![image](https://user-images.githubusercontent.com/53360337/204255844-8589b3b8-edf9-4d0f-8a62-ccba53575499.png)

4. 네트워크를 통한 2인용 게임   
    서버와 클라이언트 클래스를 정의하고 이를 바탕으로 네트워크를 이용하여 2인용 게임을 만든다. 게임의 승리 조건은 상대방이 지뢰를 선택하거나 자신이 먼저 게임을 성공할 경우 이 2가지이다.

    <div style="overflow: hidden">

    <img src="https://user-images.githubusercontent.com/53360337/204256118-0e6c6308-872c-4093-a1e9-1ce28b9a8454.png" width="300px" height="300px" style="float: left;" />
        
    <img src="https://user-images.githubusercontent.com/53360337/204256213-101a87b6-871a-4742-b1a9-f16686278171.png" width="300px" height="300px" style="float: right;" />

    <img src="https://user-images.githubusercontent.com/53360337/204256816-5ac5ffaa-a511-489a-aef0-6e19218aaa6e.png" width="300px" height="300px" style="float: right;" />
    
    </div>


5. 목숨 기능   
    GameWindow에 목숨 속성을 추가해 지뢰를 밟았을 때 작동하는 메소드에서 목숨의 개수가 한 개씩 줄도록 설정했고 난이도에 따라 생성자에서 목숨의 개수를 초기화 한다. Easy는 목숨 1개 Medium은 목숨 2개 Hard는 목숨 3개를 설정했다. Challenge와 Multi는 1개, Custom은 입력하도록 설정했다

    ![image](https://user-images.githubusercontent.com/53360337/204257718-3acc4f21-2e2c-4da1-9f80-3c3c553fde3f.png)

6. Challenge 모드   
    Challenge 모드는 클래스로 만들어 메인 화면에 버튼을 누르면 객체를 생성하는 방식으로 설계하였고 목숨 1개로 시작하며 죽지 않으면 계속 일정 개수의 버튼과 폭탄이 늘어나는 방식으로 설계하였다.

    ![image](https://user-images.githubusercontent.com/53360337/204257598-6ef57d4c-0fd6-43e0-834f-b5c843952617.png)