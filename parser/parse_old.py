#encoding=UTF-8
from bs4 import BeautifulSoup
import requests
import csv, codecs, cStringIO
import sys
import traceback

def write(page, line):
	# init CSV file #
	csv_file = open('data_ratebeer_additional.csv', "a")
	csv_file.write('"'+'","'.join([v.encode('utf-8') for v in line])+'"\n')	
	csv_file.close()
	print ("PAGE %d / %d : %s")%(page, end_page, line[1])


def parse(page):
	try:
		# init Soup #
		url = "http://www.ratebeer.com/beer/"+str(page)+"/"

		r = requests.get(url)
		r.encoding = 'utf-8'

		if r.headers.get('content-length') == str(166):
			write(page, [str(page), "404 Not Found"])
			return

		if r.headers.get('content-length') == str(1005):
			write(page, [str(page), "404 Not Found"])
			return

		if r.status_code == 400:
			write(page, [str(page), "400 Bad Request"])
			return

		data = r.text
		soup = BeautifulSoup(data) #, from_encoding="iso-8859-1"

		# Core #
		table = soup.find_all('table')[2]

		title_eng = r.url.decode('utf-8').split('/')[len(names) - 3].replace('-', " ").title()

		line = []
		title = ""
		img_url = ""
		brewed_by = ""
		style = ""
		made_in = ""
		serve_in = ""
		est_calories = ""
		abv = ""

		h1 = (table.find('h1', {"itemprop":"itemreviewed"}))
		if h1 == None:
			h1 = soup.find('h1')
			h2 = soup.find('h2')
			title = h1.get_text() + h2.get_text()
			line = [ str(page), title ]
			write(page, line)
			return
		title = h1.get_text() 

		if h1.next_sibling != None:
			title += h1.next_sibling.next_sibling.get_text()

		img_url = (table.find('img', {"id": "beerImg"}).get("src"))			

		bigs = table.find_all('big')
		for big in bigs:
			if "Brewed by " in big.get_text():
				brewed_by = (big.get_text().replace("Brewed by ",""))			
				div = big.find_parent('div')
				big.extract()
				style = (div.find('a').get_text())							
				div.find('a').extract()
				made_in  = (div.get_text().strip()[7:])						

				next_div = div.next_sibling
				if "Serve in " in (next_div.get_text()):
					serve_in = (next_div.get_text().replace("Serve in ", ""))	
				break
			elif "Formerly brewed at " in big.get_text():
				brewed_by = (big.get_text().replace("Formerly brewed at ",""))			
				div = big.find_parent('div')
				big.extract()
				style = (div.find('a').get_text())							
				div.find('a').extract()
				made_in  = (div.get_text().strip()[7:])						

				next_div = div.next_sibling
				if "Serve in " in (next_div.get_text()):
					serve_in = (next_div.get_text().replace("Serve in ", ""))	
				break
			elif "Brewed at " in big.get_text():
				brewed_by = (big.get_text()).replace("Brewed at","(Brewed at") + ")"			
				div = big.find_parent('div')
				big.extract()
				style = (div.find('a').get_text())							
				div.find('a').extract()
				made_in  = (div.get_text().strip()[7:])						

				next_div = div.next_sibling
				if "Serve in " in (next_div.get_text()):
					serve_in = (next_div.get_text().replace("Serve in ", ""))	
				break
			
		abbr = table.find('abbr', text="EST. CALORIES")
		if (abbr != None) and (abbr.next_sibling != None):
			est_calories = (abbr.next_sibling.next_sibling.get_text())	

		abbr = table.find('abbr', text="ABV")
		if abbr != None:
			abv = (abbr.next_sibling.next_sibling.get_text())				

		line = [ str(page) , title_eng, title, img_url, brewed_by, style, made_in, serve_in, est_calories, abv]
		#print line
		write(page, line)
		

		"""
		div_cd = table.find('small', text="COMMERCIAL DESCRIPTION").find_parent('div')
		div_cd.find('small').extract()
		commercial_description =  div_cd.get_text()
		line.append(commercial_description)
		
		"""

	except:
		print ("\nPAGE %d is NOT parsed!!!!!!" % page)
		exc_type, exc_value, exc_traceback = sys.exc_info()
    		lines = traceback.format_exception(exc_type, exc_value, exc_traceback)
    		print ''.join('!! ' + line for line in lines)  # Log it or whatever here

"""
not_parsed_list_file = open('data_not_parsed.csv', "rb")
not_parsed_list_reader = csv.reader(not_parsed_list_file, delimiter=",", quotechar="\"")

for row in not_parsed_list_reader:
	parse(int(row[0]))
"""

#parse(55724)


#
"""
for page in range(20001, end_page + 1):
	parse(page)
"""