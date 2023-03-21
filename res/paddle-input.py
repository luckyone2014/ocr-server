from paddleocr import PaddleOCR, draw_ocr
import sys

#print ('参数个数为:', len(sys.argv), '个参数。')
#print ('参数列表:', str(sys.argv))

# Paddleocr目前支持的多语言语种可以通过修改lang参数进行切换
# 例如`ch`, `en`, `fr`, `german`, `korean`, `japan`
ocr = PaddleOCR(use_angle_cls=True, lang="ch", show_log = False)  # need to run only once to download and load model into memory

for i in range(1, len(sys.argv)):
  img_path = sys.argv[i]
  result = ocr.ocr(img_path, cls=True)
  for idx in range(len(result)):
      res = result[idx] 
      print("---", res[1])     #中文及置信度

