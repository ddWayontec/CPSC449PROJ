module Parser where
import System.IO  
import Control.Monad
import System.Environment
import Data.List
import Data.String

removeEndSpace :: String -> String
removeEndSpace [] = []
removeEndSpace (x)   | last x == ' ' = removeEndSpace (init x)
                     | last x == '\t' = removeEndSpace (init x)
                     | otherwise = x

--removeStartSpace :: String -> String
--removeStartSpace [] = []
--removeStartSpace (x)   | head x == ' ' = removeStartSpace (tail x)
--                       | head x == '\t' = removeStartSpace (tail x)

--removeBlankLine :: [String] -> [String]
--removeBlankLine (x:xs)  | x /= "" = x:(removeBlankLine xs)
--                       | otherwise = removeBlankLine xs

removeBlankLine :: [String] -> [String]
removeBlankLine (x:xs) = [ x | x <- xs, not (null x) ]

keepFromTo :: String -> String -> [String] -> [String]
keepFromTo _ _ [] = []
keepFromTo s e a = cutFront s (cutEnd e a)

cutEnd :: String -> [String] -> [String]
cutEnd _ [] = []
cutEnd w (x:xs)   | x /= w = x:(cutEnd w xs)
                  | otherwise = []

cutFront :: String -> [String] -> [String]
cutFront _ [] = []
cutFront w (x:xs)   | x /= w = cutFront w xs
                    | otherwise = xs

deleteR :: String -> String
deleteR [] = []
deleteR (x:xs)  | x == '\r' = deleteR xs
                | otherwise = x:(deleteR xs)
               
isAllPairs :: [String] -> Bool
isAllPairs [] = True --NOT SURE WHY THIS HAS TO BE TRUE??
isAllPairs (x:xs)| (((head x) == '(') && ((last x) == ')') && ((countCommas x) == 1)) && (isAllPairs xs)  = True 
                 |otherwise = False

isValidPairMT :: [String] -> Bool
isValidPairMT [] = True
isValidPairMT (('(':a:',':b:')':[]):xs)| (isValidMachine a && isValidTask b) &&  (isValidPairMT xs) = True
                                     |otherwise = False

isValidPairTT :: [String] -> Bool
isValidPairTT [] = True
isValidPairTT (('(':a:',':b:')':[]):xs)| (isValidTask a && isValidTask b) &&  (isValidPairTT xs) = True
                                     |otherwise = False
                                     
--isValidPairTTP :: [String] -> Bool
--isValidPairTTP [] = True
--isValidPairTTP (('(':a:',':b:',':c:')':[]):xs)| (isValidTask a && isValidTask b && (isNumber c)) &&  (isValidPairTT xs) = True
--otherwise = False

isAllTriples :: [String] -> Bool
isAllTriples [] = True
isAllTriples (x:xs)| (((head x) == '(') && ((last x) == ')') && ((countCommas x) == 2)) && (isAllTriples xs)  = True 
                   |otherwise = False
                 
countCommas :: String -> Int
countCommas [] = 0
countCommas (x:xs)| (x == ',') = 1 + (countCommas xs)
                  |otherwise = 0 + (countCommas xs)
                  
isCorrectMachinePen :: [String] -> Bool
isCorrectMachinePen [] = True                                                                               
isCorrectMachinePen (x:xs)|(length x) == 15 && (isCorrectMachinePen xs) = True
                          |otherwise = False
                       
isValidMachine :: Char -> Bool
isValidMachine a| a == '1' = True
                | a == '2' = True
                | a == '3' = True
                | a == '4' = True
                | a == '5' = True
                | a == '6' = True
                | a == '7' = True
                | a == '8' = True
                | otherwise = False

isValidTask :: Char -> Bool
isValidTask a| a == 'A' = True
             | a == 'B' = True
             | a == 'C' = True
             | a == 'D' = True
             | a == 'E' = True
             | a == 'F' = True
             | a == 'G' = True
             | a == 'H' = True
             | otherwise = False