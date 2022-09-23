import matplotlib.pyplot as plt
import numpy as np
from pandas import DataFrame
import requests
from PIL import Image
from wordcloud import ImageColorGenerator
from wordcloud import WordCloud
from collections import Counter
from konlpy.tag import Okt


class MakeCloud():

        def wc(self, file):
                engine = Okt()
                all_nouns = engine.nouns(file)
                value_to_remove = ['장관', '위원회', '정부', '국가', '대통령령', '사항', '계획','지방자치단체','행정기관']
                nouns = [n for n in all_nouns if len(n) > 1 and n not in value_to_remove]
                count = Counter(nouns)

                tags = count.most_common(5000)
                table = DataFrame(tags)
                table.columns = ['단어', '빈도수']
                table.index = table.index + 1
                print(table.head(60))
                print(table[table['단어'] == '이산화탄소'])

                image = Image.open('6.png')
                mask = np.array(image)
                # image_color = ImageColorGenerator(mask)

                def color_func(word, font_size, position,orientation,random_state=None, **kwargs): # color_func의 기본 파라미터들
                        return ("hsl({:d},{:d}%, {:d}%)".format(np.random.randint(120, 140), np.random.randint(80, 100),
                                                        np.random.randint(10, 60)))

                wc = WordCloud(font_path="malgun", background_color="white", max_font_size=500,
                           width=300, height=100, mask=mask, color_func=color_func)
                cloud = wc.generate_from_frequencies(dict(tags))

                plt.imshow(cloud)
                plt.axis('off')
                plt.savefig('1.jpg')
                plt.show()




