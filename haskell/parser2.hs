module Parser where
import System.IO  
import Control.Monad
import System.Environment
import Data.List
import Data.String
import Data.Typeable

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
                                     
isValidTripleTTP :: [String] -> Bool
isValidTripleTTP [] = True
isValidTripleTTP (('(':a:',':b:',':c:')':[]):xs)| (isValidTask a && isValidTask b ) &&  (isValidTripleTTP xs) = True
                                               |otherwise = False

isAllTriples :: [String] -> Bool
isAllTriples [] = True
isAllTriples (x:xs)| (((head x) == '(') && ((last x) == ')') && ((countCommas x) == 2)) && (isAllTriples xs)  = True 
                   |otherwise = False

countCommas :: String -> Int
countCommas [] = 0
countCommas (x:xs)| (x == ',') = 1 + (countCommas xs)
                  |otherwise = 0 + (countCommas xs)
                       
isValidMachine :: Char -> Bool
isValidMachine a| a `elem` ['1'..'8'] = True
                |otherwise = False

isValidTask :: Char -> Bool
isValidTask a| a `elem` ['A'..'H'] = True
             |otherwise = False

isInt :: String -> Bool
isInt x |(read x) `elem` [1..] = True
           |otherwise = False
