import sys

from PyQt5.QtCore import Qt
from PyQt5.QtGui import QIcon
from PyQt5.QtWidgets import QWidget
from PyQt5.QtWidgets import QApplication
from PyQt5.QtWidgets import QLabel
from PyQt5.QtWidgets import QMainWindow
from PyQt5.QtWidgets import QStatusBar
from PyQt5.QtWidgets import QToolBar
from PyQt5.QtWidgets import QGridLayout
from PyQt5.QtWidgets import QLineEdit
from PyQt5.QtWidgets import QPushButton
from PyQt5.QtWidgets import QVBoxLayout
from PyQt5.QtWidgets import QHBoxLayout
from PyQt5.QtWidgets import QFrame


class PyCalcUi(QMainWindow):
    """PyCalc's View (GUI)."""
    def __init__(self):
        """View initializer."""
        super().__init__()
        # Set some main window's properties
        self.setWindowTitle('PyCalc')
        self.setFixedSize(500, 500)
        self.setWindowIcon(QIcon('/home/user/git/TopQuiz1/pics/tkinter_icon.gif'))
        
        self.generalLayout = QVBoxLayout()
        self._centralWidget = QWidget(self)
        self.setCentralWidget(self._centralWidget)
        self._centralWidget.setLayout(self.generalLayout)
        
        """
        helloMsg = QLabel('<h1>Hello World!</h1>', parent=self)
        helloMsg.move(60, 15)
        """
        
        questLayout = QHBoxLayout()
        btnLayout = QVBoxLayout()
        
        frame = QFrame(self)
        frame.setFixedHeight(100)
        frame.setFixedWidth(self.width() - 40)
        frame.setFrameShape(QFrame.StyledPanel)
        #frame.set
        frame.setStyleSheet("background-color:green")
        frame.move(20,20)
        #frame.setLineWidth(0.6)
        
        
        e = QLabel("?", self)
        #e.setAlignment(Qt.AlignCenter)
        e.move(0,0)
        
        b1 = QPushButton('Left', self)
        b1.setFixedWidth(self.width() - 50)
        b1.move(20,200)
        b2 = QPushButton('------------------------------')
        
        
       
        """
        #btnLayout.addWidget(frame)
        questLayout.addWidget(e)
        
        btnLayout.addWidget(b1)
        btnLayout.addWidget(b2)
        
        
        #self.setLayout(btnLayout)
        #self.generalLayout.addWidget(frame)
        self.generalLayout.addLayout(questLayout)
        self.generalLayout.addLayout(btnLayout)
        """
        
        




        


# Client code
def main():
    """Main function."""
    # Create an instance of `QApplication`
    pycalc = QApplication(sys.argv)
    view = PyCalcUi()
    view.show()
    sys.exit(pycalc.exec_())



main()