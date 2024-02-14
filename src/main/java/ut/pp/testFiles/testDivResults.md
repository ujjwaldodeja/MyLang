### Output:
```
Sprockell 0 says 0
Sprockell 0 says 4
Sprockell 0 says -9
Sprockell 0 says -4
Sprockell 0 says 0
Sprockell 0 says 9999
Sprockell 0 says 1
Sprockell 0 says 5
Sprockell 0 says 1
Error: Cannot divide by 0
```

### Spril instructions:
```
  0: Load (ImmValue 6) regB                   --Print
  1: Load (ImmValue 5) regA                   
  2: Compute Equal regB reg0 regD             --Compute division
  3: Load (ImmValue 0) regF                   
  4: Branch regD (Abs 449)                    --Branch to error
  5: Compute GtE regA reg0 regC               
  6: Compute GtE regB reg0 regD               
  7: Load (ImmValue (-1)) regE                
  8: Branch regC (Rel 2)                      
  9: Compute Mul regA regE regA               
 10: Branch regD (Rel 2)                      
 11: Compute Mul regB regE regB               
 12: Compute Xor regC regD regC               
 13: Push regC                                
 14: Load (ImmValue 0) regE                   
 15: Load (ImmValue 1) regF                   
 16: Load (ImmValue 1) regD                   
 17: Compute GtE regF regA regC               
 18: Branch regC (Rel 3)                      
 19: Compute LShift regF regD regF            
 20: Jump (Rel (-3))                          
 21: Load (ImmValue 0) regD                   
 22: Compute Equal regF reg0 regC             
 23: Branch regC (Rel 14)                     
 24: Load (ImmValue 1) regC                   
 25: Compute LShift regE regC regE            
 26: Compute And regF regA regC               
 27: Compute Equal regC reg0 regC             
 28: Branch regC (Rel 2)                      
 29: Compute Incr regE regE regE              
 30: Compute Lt regE regB regC                
 31: Branch regC (Rel 3)                      
 32: Compute Sub regE regB regE               
 33: Compute Or regD regF regD                
 34: Load (ImmValue 1) regC                   
 35: Compute RShift regF regC regF            
 36: Jump (Rel (-14))                         
 37: Pop regC                                 
 38: Compute Equal regC reg0 regC             
 39: Branch regC (Rel 3)                      
 40: Load (ImmValue (-1)) regE                
 41: Compute Mul regE regD regD               
 42: Compute Add regD reg0 regA               
 43: WriteInstr regA numberIO                 
 44: Load (ImmValue 2) regB                   --Print
 45: Load (ImmValue 8) regA                   
 46: Compute Equal regB reg0 regD             --Compute division
 47: Load (ImmValue 0) regF                   
 48: Branch regD (Abs 449)                    --Branch to error
 49: Compute GtE regA reg0 regC               
 50: Compute GtE regB reg0 regD               
 51: Load (ImmValue (-1)) regE                
 52: Branch regC (Rel 2)                      
 53: Compute Mul regA regE regA               
 54: Branch regD (Rel 2)                      
 55: Compute Mul regB regE regB               
 56: Compute Xor regC regD regC               
 57: Push regC                                
 58: Load (ImmValue 0) regE                   
 59: Load (ImmValue 1) regF                   
 60: Load (ImmValue 1) regD                   
 61: Compute GtE regF regA regC               
 62: Branch regC (Rel 3)                      
 63: Compute LShift regF regD regF            
 64: Jump (Rel (-3))                          
 65: Load (ImmValue 0) regD                   
 66: Compute Equal regF reg0 regC             
 67: Branch regC (Rel 14)                     
 68: Load (ImmValue 1) regC                   
 69: Compute LShift regE regC regE            
 70: Compute And regF regA regC               
 71: Compute Equal regC reg0 regC             
 72: Branch regC (Rel 2)                      
 73: Compute Incr regE regE regE              
 74: Compute Lt regE regB regC                
 75: Branch regC (Rel 3)                      
 76: Compute Sub regE regB regE               
 77: Compute Or regD regF regD                
 78: Load (ImmValue 1) regC                   
 79: Compute RShift regF regC regF            
 80: Jump (Rel (-14))                         
 81: Pop regC                                 
 82: Compute Equal regC reg0 regC             
 83: Branch regC (Rel 3)                      
 84: Load (ImmValue (-1)) regE                
 85: Compute Mul regE regD regD               
 86: Compute Add regD reg0 regA               
 87: WriteInstr regA numberIO                 
 88: Load (ImmValue (-1)) regB                --Print
 89: Load (ImmValue 9) regA                   
 90: Compute Equal regB reg0 regD             --Compute division
 91: Load (ImmValue 0) regF                   
 92: Branch regD (Abs 449)                    --Branch to error
 93: Compute GtE regA reg0 regC               
 94: Compute GtE regB reg0 regD               
 95: Load (ImmValue (-1)) regE                
 96: Branch regC (Rel 2)                      
 97: Compute Mul regA regE regA               
 98: Branch regD (Rel 2)                      
 99: Compute Mul regB regE regB               
100: Compute Xor regC regD regC               
101: Push regC                                
102: Load (ImmValue 0) regE                   
103: Load (ImmValue 1) regF                   
104: Load (ImmValue 1) regD                   
105: Compute GtE regF regA regC               
106: Branch regC (Rel 3)                      
107: Compute LShift regF regD regF            
108: Jump (Rel (-3))                          
109: Load (ImmValue 0) regD                   
110: Compute Equal regF reg0 regC             
111: Branch regC (Rel 14)                     
112: Load (ImmValue 1) regC                   
113: Compute LShift regE regC regE            
114: Compute And regF regA regC               
115: Compute Equal regC reg0 regC             
116: Branch regC (Rel 2)                      
117: Compute Incr regE regE regE              
118: Compute Lt regE regB regC                
119: Branch regC (Rel 3)                      
120: Compute Sub regE regB regE               
121: Compute Or regD regF regD                
122: Load (ImmValue 1) regC                   
123: Compute RShift regF regC regF            
124: Jump (Rel (-14))                         
125: Pop regC                                 
126: Compute Equal regC reg0 regC             
127: Branch regC (Rel 3)                      
128: Load (ImmValue (-1)) regE                
129: Compute Mul regE regD regD               
130: Compute Add regD reg0 regA               
131: WriteInstr regA numberIO                 
132: Load (ImmValue 2) regB                   --Print
133: Load (ImmValue (-9)) regA                
134: Compute Equal regB reg0 regD             --Compute division
135: Load (ImmValue 0) regF                   
136: Branch regD (Abs 449)                    --Branch to error
137: Compute GtE regA reg0 regC               
138: Compute GtE regB reg0 regD               
139: Load (ImmValue (-1)) regE                
140: Branch regC (Rel 2)                      
141: Compute Mul regA regE regA               
142: Branch regD (Rel 2)                      
143: Compute Mul regB regE regB               
144: Compute Xor regC regD regC               
145: Push regC                                
146: Load (ImmValue 0) regE                   
147: Load (ImmValue 1) regF                   
148: Load (ImmValue 1) regD                   
149: Compute GtE regF regA regC               
150: Branch regC (Rel 3)                      
151: Compute LShift regF regD regF            
152: Jump (Rel (-3))                          
153: Load (ImmValue 0) regD                   
154: Compute Equal regF reg0 regC             
155: Branch regC (Rel 14)                     
156: Load (ImmValue 1) regC                   
157: Compute LShift regE regC regE            
158: Compute And regF regA regC               
159: Compute Equal regC reg0 regC             
160: Branch regC (Rel 2)                      
161: Compute Incr regE regE regE              
162: Compute Lt regE regB regC                
163: Branch regC (Rel 3)                      
164: Compute Sub regE regB regE               
165: Compute Or regD regF regD                
166: Load (ImmValue 1) regC                   
167: Compute RShift regF regC regF            
168: Jump (Rel (-14))                         
169: Pop regC                                 
170: Compute Equal regC reg0 regC             
171: Branch regC (Rel 3)                      
172: Load (ImmValue (-1)) regE                
173: Compute Mul regE regD regD               
174: Compute Add regD reg0 regA               
175: WriteInstr regA numberIO                 
176: Load (ImmValue 10) regB                  --Print
177: Load (ImmValue 0) regA                   
178: Compute Equal regB reg0 regD             --Compute division
179: Load (ImmValue 0) regF                   
180: Branch regD (Abs 449)                    --Branch to error
181: Compute GtE regA reg0 regC               
182: Compute GtE regB reg0 regD               
183: Load (ImmValue (-1)) regE                
184: Branch regC (Rel 2)                      
185: Compute Mul regA regE regA               
186: Branch regD (Rel 2)                      
187: Compute Mul regB regE regB               
188: Compute Xor regC regD regC               
189: Push regC                                
190: Load (ImmValue 0) regE                   
191: Load (ImmValue 1) regF                   
192: Load (ImmValue 1) regD                   
193: Compute GtE regF regA regC               
194: Branch regC (Rel 3)                      
195: Compute LShift regF regD regF            
196: Jump (Rel (-3))                          
197: Load (ImmValue 0) regD                   
198: Compute Equal regF reg0 regC             
199: Branch regC (Rel 14)                     
200: Load (ImmValue 1) regC                   
201: Compute LShift regE regC regE            
202: Compute And regF regA regC               
203: Compute Equal regC reg0 regC             
204: Branch regC (Rel 2)                      
205: Compute Incr regE regE regE              
206: Compute Lt regE regB regC                
207: Branch regC (Rel 3)                      
208: Compute Sub regE regB regE               
209: Compute Or regD regF regD                
210: Load (ImmValue 1) regC                   
211: Compute RShift regF regC regF            
212: Jump (Rel (-14))                         
213: Pop regC                                 
214: Compute Equal regC reg0 regC             
215: Branch regC (Rel 3)                      
216: Load (ImmValue (-1)) regE                
217: Compute Mul regE regD regD               
218: Compute Add regD reg0 regA               
219: WriteInstr regA numberIO                 
220: Load (ImmValue 1) regB                   --Print
221: Load (ImmValue 9999) regA                
222: Compute Equal regB reg0 regD             --Compute division
223: Load (ImmValue 0) regF                   
224: Branch regD (Abs 449)                    --Branch to error
225: Compute GtE regA reg0 regC               
226: Compute GtE regB reg0 regD               
227: Load (ImmValue (-1)) regE                
228: Branch regC (Rel 2)                      
229: Compute Mul regA regE regA               
230: Branch regD (Rel 2)                      
231: Compute Mul regB regE regB               
232: Compute Xor regC regD regC               
233: Push regC                                
234: Load (ImmValue 0) regE                   
235: Load (ImmValue 1) regF                   
236: Load (ImmValue 1) regD                   
237: Compute GtE regF regA regC               
238: Branch regC (Rel 3)                      
239: Compute LShift regF regD regF            
240: Jump (Rel (-3))                          
241: Load (ImmValue 0) regD                   
242: Compute Equal regF reg0 regC             
243: Branch regC (Rel 14)                     
244: Load (ImmValue 1) regC                   
245: Compute LShift regE regC regE            
246: Compute And regF regA regC               
247: Compute Equal regC reg0 regC             
248: Branch regC (Rel 2)                      
249: Compute Incr regE regE regE              
250: Compute Lt regE regB regC                
251: Branch regC (Rel 3)                      
252: Compute Sub regE regB regE               
253: Compute Or regD regF regD                
254: Load (ImmValue 1) regC                   
255: Compute RShift regF regC regF            
256: Jump (Rel (-14))                         
257: Pop regC                                 
258: Compute Equal regC reg0 regC             
259: Branch regC (Rel 3)                      
260: Load (ImmValue (-1)) regE                
261: Compute Mul regE regD regD               
262: Compute Add regD reg0 regA               
263: WriteInstr regA numberIO                 
264: Load (ImmValue 9) regB                   --Print
265: Load (ImmValue 9) regA                   
266: Compute Equal regB reg0 regD             --Compute division
267: Load (ImmValue 0) regF                   
268: Branch regD (Abs 449)                    --Branch to error
269: Compute GtE regA reg0 regC               
270: Compute GtE regB reg0 regD               
271: Load (ImmValue (-1)) regE                
272: Branch regC (Rel 2)                      
273: Compute Mul regA regE regA               
274: Branch regD (Rel 2)                      
275: Compute Mul regB regE regB               
276: Compute Xor regC regD regC               
277: Push regC                                
278: Load (ImmValue 0) regE                   
279: Load (ImmValue 1) regF                   
280: Load (ImmValue 1) regD                   
281: Compute GtE regF regA regC               
282: Branch regC (Rel 3)                      
283: Compute LShift regF regD regF            
284: Jump (Rel (-3))                          
285: Load (ImmValue 0) regD                   
286: Compute Equal regF reg0 regC             
287: Branch regC (Rel 14)                     
288: Load (ImmValue 1) regC                   
289: Compute LShift regE regC regE            
290: Compute And regF regA regC               
291: Compute Equal regC reg0 regC             
292: Branch regC (Rel 2)                      
293: Compute Incr regE regE regE              
294: Compute Lt regE regB regC                
295: Branch regC (Rel 3)                      
296: Compute Sub regE regB regE               
297: Compute Or regD regF regD                
298: Load (ImmValue 1) regC                   
299: Compute RShift regF regC regF            
300: Jump (Rel (-14))                         
301: Pop regC                                 
302: Compute Equal regC reg0 regC             
303: Branch regC (Rel 3)                      
304: Load (ImmValue (-1)) regE                
305: Compute Mul regE regD regD               
306: Compute Add regD reg0 regA               
307: WriteInstr regA numberIO                 
308: Load (ImmValue (-200)) regA              --Assignment to x
309: Store regA (DirAddr 0)                   
310: Load (ImmValue 0) regA                   --Assignment of array to y
311: Store regA (DirAddr 1)                   
312: Load (ImmValue (-40)) regA               
313: Store regA (DirAddr 2)                   
314: Load (ImmValue (-350)) regA              
315: Store regA (DirAddr 3)                   
316: Load (DirAddr 2) regB                    --(Loading array index y at offset 1) // Print
317: Load (DirAddr 0) regA                    --(Loading var x)
318: Compute Equal regB reg0 regD             --Compute division
319: Load (ImmValue 0) regF                   
320: Branch regD (Abs 449)                    --Branch to error
321: Compute GtE regA reg0 regC               
322: Compute GtE regB reg0 regD               
323: Load (ImmValue (-1)) regE                
324: Branch regC (Rel 2)                      
325: Compute Mul regA regE regA               
326: Branch regD (Rel 2)                      
327: Compute Mul regB regE regB               
328: Compute Xor regC regD regC               
329: Push regC                                
330: Load (ImmValue 0) regE                   
331: Load (ImmValue 1) regF                   
332: Load (ImmValue 1) regD                   
333: Compute GtE regF regA regC               
334: Branch regC (Rel 3)                      
335: Compute LShift regF regD regF            
336: Jump (Rel (-3))                          
337: Load (ImmValue 0) regD                   
338: Compute Equal regF reg0 regC             
339: Branch regC (Rel 14)                     
340: Load (ImmValue 1) regC                   
341: Compute LShift regE regC regE            
342: Compute And regF regA regC               
343: Compute Equal regC reg0 regC             
344: Branch regC (Rel 2)                      
345: Compute Incr regE regE regE              
346: Compute Lt regE regB regC                
347: Branch regC (Rel 3)                      
348: Compute Sub regE regB regE               
349: Compute Or regD regF regD                
350: Load (ImmValue 1) regC                   
351: Compute RShift regF regC regF            
352: Jump (Rel (-14))                         
353: Pop regC                                 
354: Compute Equal regC reg0 regC             
355: Branch regC (Rel 3)                      
356: Load (ImmValue (-1)) regE                
357: Compute Mul regE regD regD               
358: Compute Add regD reg0 regA               
359: WriteInstr regA numberIO                 
360: Load (DirAddr 0) regB                    --(Loading var x) // Print
361: Load (DirAddr 3) regA                    --(Loading array index y at offset 2)
362: Compute Equal regB reg0 regD             --Compute division
363: Load (ImmValue 0) regF                   
364: Branch regD (Abs 449)                    --Branch to error
365: Compute GtE regA reg0 regC               
366: Compute GtE regB reg0 regD               
367: Load (ImmValue (-1)) regE                
368: Branch regC (Rel 2)                      
369: Compute Mul regA regE regA               
370: Branch regD (Rel 2)                      
371: Compute Mul regB regE regB               
372: Compute Xor regC regD regC               
373: Push regC                                
374: Load (ImmValue 0) regE                   
375: Load (ImmValue 1) regF                   
376: Load (ImmValue 1) regD                   
377: Compute GtE regF regA regC               
378: Branch regC (Rel 3)                      
379: Compute LShift regF regD regF            
380: Jump (Rel (-3))                          
381: Load (ImmValue 0) regD                   
382: Compute Equal regF reg0 regC             
383: Branch regC (Rel 14)                     
384: Load (ImmValue 1) regC                   
385: Compute LShift regE regC regE            
386: Compute And regF regA regC               
387: Compute Equal regC reg0 regC             
388: Branch regC (Rel 2)                      
389: Compute Incr regE regE regE              
390: Compute Lt regE regB regC                
391: Branch regC (Rel 3)                      
392: Compute Sub regE regB regE               
393: Compute Or regD regF regD                
394: Load (ImmValue 1) regC                   
395: Compute RShift regF regC regF            
396: Jump (Rel (-14))                         
397: Pop regC                                 
398: Compute Equal regC reg0 regC             
399: Branch regC (Rel 3)                      
400: Load (ImmValue (-1)) regE                
401: Compute Mul regE regD regD               
402: Compute Add regD reg0 regA               
403: WriteInstr regA numberIO                 
404: Load (ImmValue 0) regB                   --Print
405: Load (ImmValue 8) regA                   
406: Compute Equal regB reg0 regD             --Compute division
407: Load (ImmValue 0) regF                   
408: Branch regD (Abs 449)                    --Branch to error
409: Compute GtE regA reg0 regC               
410: Compute GtE regB reg0 regD               
411: Load (ImmValue (-1)) regE                
412: Branch regC (Rel 2)                      
413: Compute Mul regA regE regA               
414: Branch regD (Rel 2)                      
415: Compute Mul regB regE regB               
416: Compute Xor regC regD regC               
417: Push regC                                
418: Load (ImmValue 0) regE                   
419: Load (ImmValue 1) regF                   
420: Load (ImmValue 1) regD                   
421: Compute GtE regF regA regC               
422: Branch regC (Rel 3)                      
423: Compute LShift regF regD regF            
424: Jump (Rel (-3))                          
425: Load (ImmValue 0) regD                   
426: Compute Equal regF reg0 regC             
427: Branch regC (Rel 14)                     
428: Load (ImmValue 1) regC                   
429: Compute LShift regE regC regE            
430: Compute And regF regA regC               
431: Compute Equal regC reg0 regC             
432: Branch regC (Rel 2)                      
433: Compute Incr regE regE regE              
434: Compute Lt regE regB regC                
435: Branch regC (Rel 3)                      
436: Compute Sub regE regB regE               
437: Compute Or regD regF regD                
438: Load (ImmValue 1) regC                   
439: Compute RShift regF regC regF            
440: Jump (Rel (-14))                         
441: Pop regC                                 
442: Compute Equal regC reg0 regC             
443: Branch regC (Rel 3)                      
444: Load (ImmValue (-1)) regE                
445: Compute Mul regE regD regD               
446: Compute Add regD reg0 regA               
447: WriteInstr regA numberIO                 
448: EndProg                                  
449: Load (ImmValue 0) regA                   --Error Handling
450: Compute NEq regF regA regB               
451: Branch regB (Rel 51)                     
452: Load (ImmValue 69) regA                  --Print error DivByZero
453: WriteInstr regA charIO                   
454: Load (ImmValue 114) regA                 
455: WriteInstr regA charIO                   
456: Load (ImmValue 114) regA                 
457: WriteInstr regA charIO                   
458: Load (ImmValue 111) regA                 
459: WriteInstr regA charIO                   
460: Load (ImmValue 114) regA                 
461: WriteInstr regA charIO                   
462: Load (ImmValue 58) regA                  
463: WriteInstr regA charIO                   
464: Load (ImmValue 32) regA                  
465: WriteInstr regA charIO                   
466: Load (ImmValue 67) regA                  
467: WriteInstr regA charIO                   
468: Load (ImmValue 97) regA                  
469: WriteInstr regA charIO                   
470: Load (ImmValue 110) regA                 
471: WriteInstr regA charIO                   
472: Load (ImmValue 110) regA                 
473: WriteInstr regA charIO                   
474: Load (ImmValue 111) regA                 
475: WriteInstr regA charIO                   
476: Load (ImmValue 116) regA                 
477: WriteInstr regA charIO                   
478: Load (ImmValue 32) regA                  
479: WriteInstr regA charIO                   
480: Load (ImmValue 100) regA                 
481: WriteInstr regA charIO                   
482: Load (ImmValue 105) regA                 
483: WriteInstr regA charIO                   
484: Load (ImmValue 118) regA                 
485: WriteInstr regA charIO                   
486: Load (ImmValue 105) regA                 
487: WriteInstr regA charIO                   
488: Load (ImmValue 100) regA                 
489: WriteInstr regA charIO                   
490: Load (ImmValue 101) regA                 
491: WriteInstr regA charIO                   
492: Load (ImmValue 32) regA                  
493: WriteInstr regA charIO                   
494: Load (ImmValue 98) regA                  
495: WriteInstr regA charIO                   
496: Load (ImmValue 121) regA                 
497: WriteInstr regA charIO                   
498: Load (ImmValue 32) regA                  
499: WriteInstr regA charIO                   
500: Load (ImmValue 48) regA                  
501: WriteInstr regA charIO                   
502: Load (ImmValue 1) regA                   
503: Compute NEq regF regA regB               
504: Branch regB (Rel 113)                    
505: Load (ImmValue 69) regA                  --Print error ArrayIndexOutOfBounds
506: WriteInstr regA charIO                   
507: Load (ImmValue 114) regA                 
508: WriteInstr regA charIO                   
509: Load (ImmValue 114) regA                 
510: WriteInstr regA charIO                   
511: Load (ImmValue 111) regA                 
512: WriteInstr regA charIO                   
513: Load (ImmValue 114) regA                 
514: WriteInstr regA charIO                   
515: Load (ImmValue 58) regA                  
516: WriteInstr regA charIO                   
517: Load (ImmValue 32) regA                  
518: WriteInstr regA charIO                   
519: Load (ImmValue 65) regA                  
520: WriteInstr regA charIO                   
521: Load (ImmValue 116) regA                 
522: WriteInstr regA charIO                   
523: Load (ImmValue 116) regA                 
524: WriteInstr regA charIO                   
525: Load (ImmValue 101) regA                 
526: WriteInstr regA charIO                   
527: Load (ImmValue 109) regA                 
528: WriteInstr regA charIO                   
529: Load (ImmValue 112) regA                 
530: WriteInstr regA charIO                   
531: Load (ImmValue 116) regA                 
532: WriteInstr regA charIO                   
533: Load (ImmValue 105) regA                 
534: WriteInstr regA charIO                   
535: Load (ImmValue 110) regA                 
536: WriteInstr regA charIO                   
537: Load (ImmValue 103) regA                 
538: WriteInstr regA charIO                   
539: Load (ImmValue 32) regA                  
540: WriteInstr regA charIO                   
541: Load (ImmValue 116) regA                 
542: WriteInstr regA charIO                   
543: Load (ImmValue 111) regA                 
544: WriteInstr regA charIO                   
545: Load (ImmValue 32) regA                  
546: WriteInstr regA charIO                   
547: Load (ImmValue 97) regA                  
548: WriteInstr regA charIO                   
549: Load (ImmValue 99) regA                  
550: WriteInstr regA charIO                   
551: Load (ImmValue 99) regA                  
552: WriteInstr regA charIO                   
553: Load (ImmValue 101) regA                 
554: WriteInstr regA charIO                   
555: Load (ImmValue 115) regA                 
556: WriteInstr regA charIO                   
557: Load (ImmValue 115) regA                 
558: WriteInstr regA charIO                   
559: Load (ImmValue 32) regA                  
560: WriteInstr regA charIO                   
561: Load (ImmValue 111) regA                 
562: WriteInstr regA charIO                   
563: Load (ImmValue 117) regA                 
564: WriteInstr regA charIO                   
565: Load (ImmValue 116) regA                 
566: WriteInstr regA charIO                   
567: Load (ImmValue 32) regA                  
568: WriteInstr regA charIO                   
569: Load (ImmValue 111) regA                 
570: WriteInstr regA charIO                   
571: Load (ImmValue 102) regA                 
572: WriteInstr regA charIO                   
573: Load (ImmValue 32) regA                  
574: WriteInstr regA charIO                   
575: Load (ImmValue 98) regA                  
576: WriteInstr regA charIO                   
577: Load (ImmValue 111) regA                 
578: WriteInstr regA charIO                   
579: Load (ImmValue 117) regA                 
580: WriteInstr regA charIO                   
581: Load (ImmValue 110) regA                 
582: WriteInstr regA charIO                   
583: Load (ImmValue 100) regA                 
584: WriteInstr regA charIO                   
585: Load (ImmValue 115) regA                 
586: WriteInstr regA charIO                   
587: Load (ImmValue 32) regA                  
588: WriteInstr regA charIO                   
589: Load (ImmValue 105) regA                 
590: WriteInstr regA charIO                   
591: Load (ImmValue 110) regA                 
592: WriteInstr regA charIO                   
593: Load (ImmValue 100) regA                 
594: WriteInstr regA charIO                   
595: Load (ImmValue 101) regA                 
596: WriteInstr regA charIO                   
597: Load (ImmValue 120) regA                 
598: WriteInstr regA charIO                   
599: Load (ImmValue 32) regA                  
600: WriteInstr regA charIO                   
601: Load (ImmValue 111) regA                 
602: WriteInstr regA charIO                   
603: Load (ImmValue 102) regA                 
604: WriteInstr regA charIO                   
605: Load (ImmValue 32) regA                  
606: WriteInstr regA charIO                   
607: Load (ImmValue 97) regA                  
608: WriteInstr regA charIO                   
609: Load (ImmValue 114) regA                 
610: WriteInstr regA charIO                   
611: Load (ImmValue 114) regA                 
612: WriteInstr regA charIO                   
613: Load (ImmValue 97) regA                  
614: WriteInstr regA charIO                   
615: Load (ImmValue 121) regA                 
616: WriteInstr regA charIO                   
617: EndProg             
``` 