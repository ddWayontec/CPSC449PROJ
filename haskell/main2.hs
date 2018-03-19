module Main where
import System.IO  
import Control.Monad
import System.Environment
import Parser
import System.Exit (exitSuccess)
import Data.Typeable
main = do

  args <- getArgs
  contents <- readFile (head args)
  let linesOfContent = removeBlankLine (map removeEndSpace (map deleteR (lines contents)))

  putStr "linesOfContent: "
  putStrLn (show linesOfContent)

  --let nameSection = keepFromTo "" "forced partial assignment:" linesOfContent
  let forcedSection =  keepFromTo "forced partial assignment:" "forbidden machine:" linesOfContent
  let forbiddenSection = keepFromTo "forbidden machine:" "too-near tasks:" linesOfContent
  let tooNearTaskSection = keepFromTo "too-near tasks:" "machine penalties:" linesOfContent
  let machPenSection = map (map read) (map words (keepFromTo "machine penalties:" "too-near penalities" linesOfContent)) :: [[Int]] 
  let tooNearPenSection = keepFromTo "too-near penalities" "" linesOfContent

  
  let toOutput = if not((length machPenSection) == 8) || not((sum(map length machPenSection)) == 64)
                 then "machine penalty error"
                 else if not(isAllPairs forcedSection) || not(isAllPairs forbiddenSection) || not(isAllTriples tooNearPenSection)
                      then "Error in form"
                      else if not(isValidPairMT forcedSection) || not(isValidPairMT forbiddenSection) || not(isValidPairTT tooNearTaskSection)
                           then "invalid machine/task"
                           else if not(isValidPairTT tooNearTaskSection)
                                then "invalid task"
                                else if not(isValidTripleTTP tooNearPenSection)
                                     then "invalid task"
                                     else  ""
                             
  writeFile (last args) toOutput
